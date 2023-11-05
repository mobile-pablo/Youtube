package com.mobile.pablo.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.domain.mapper.popular.PopularMapper
import com.mobile.pablo.domain.mapper.search.SearchItemMapper
import com.mobile.pablo.domain.mapper.search.SearchMapper
import com.mobile.pablo.domain.model.popular.Popular
import com.mobile.pablo.domain.model.search.Search
import com.mobile.pablo.networking.source.popular.PopularDataSource
import com.mobile.pablo.networking.source.popular.paging.PopularPagingSource
import com.mobile.pablo.networking.source.search.SearchDataSource
import com.mobile.pablo.storage.sharedprefs.SharedPreferencesManager
import com.mobile.pablo.storage.source.search.SearchDataStorage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class VideosUseCase {

    class GetSearchVideos @Inject constructor(
        private val searchDataSource: SearchDataSource,
        private val searchDataStorage: SearchDataStorage,
        private val searchMapper: SearchMapper,
        private val searchItemMapper: SearchItemMapper
    ) : VideosUseCase() {

        suspend operator fun invoke(query: String): DataTransfer<Search> {
            val searchResponse = searchDataSource.getSearchVideos(query)

            return when {
                searchResponse.isSuccessful -> {
                    val items = searchResponse.data!!
                    val search = searchMapper.map(searchResponse.data)
                    searchDataStorage.insertSearch(items)
                    DataTransfer(data = search)
                }

                searchResponse.isServiceUnavailable -> {
                    val searchLocal = searchDataStorage.getSearch()!!.items
                    val searchLocalDTO = searchLocal!!.map(searchItemMapper::map)
                    DataTransfer(data = Search(etag = EMPTY_STRING, items = searchLocalDTO))
                }

                else -> DataTransfer(error = searchResponse.error)
            }
        }
    }

    class GetPopularVideos @Inject constructor(
        private val popularDataSource: PopularDataSource,
        private val popularMapper: PopularMapper,
        private val sharedPreferencesManager: SharedPreferencesManager
    ) : VideosUseCase() {

        operator fun invoke(): Flow<PagingData<Popular>> {
            return Pager(
                config = PagingConfig(pageSize = 20, prefetchDistance = 2),
                pagingSourceFactory = { PopularPagingSource(popularDataSource, sharedPreferencesManager) }
            ).flow.map { pagingData ->
                pagingData.map { dataTransfer ->
                    popularMapper.map(dataTransfer.data)!!
                }
            }
        }
    }
}