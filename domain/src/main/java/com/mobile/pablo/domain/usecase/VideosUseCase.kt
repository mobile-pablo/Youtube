package com.mobile.pablo.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.domain.const.PAGE_ENABLE_PLACEHOLDERS
import com.mobile.pablo.domain.const.PAGE_PREFETCH_DISTANCE
import com.mobile.pablo.domain.const.PAGE_SIZE
import com.mobile.pablo.domain.mapper.popular.PopularItemMapper
import com.mobile.pablo.domain.mapper.search.SearchMapper
import com.mobile.pablo.domain.model.popular.PopularItem
import com.mobile.pablo.domain.model.search.Search
import com.mobile.pablo.networking.source.popular.PopularDataSource
import com.mobile.pablo.networking.source.popular.PopularPagingSource
import com.mobile.pablo.networking.source.search.SearchDataSource
import com.mobile.pablo.storage.source.search.SearchDataStorage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class VideosUseCase {

    class GetSearchVideos @Inject constructor(
        private val searchDataSource: SearchDataSource,
        private val searchDataStorage: SearchDataStorage,
        private val searchMapper: SearchMapper
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

                else -> {
                    try {
                        val searchLocal = searchDataStorage.getSearch()
                        val searchLocalDTO = searchMapper.map(searchLocal)
                        DataTransfer(data = searchLocalDTO)
                    } catch (e: Exception) {
                        DataTransfer(error = searchResponse.error)
                    }
                }
            }
        }
    }

    class GetPopularVideos @Inject constructor(
        private val popularDataSource: PopularDataSource,
        private val popularItemMapper: PopularItemMapper
    ) : VideosUseCase() {

        operator fun invoke(): Flow<PagingData<PopularItem>> {
            return Pager(
                config = PagingConfig(
                    pageSize = PAGE_SIZE,
                    prefetchDistance = PAGE_PREFETCH_DISTANCE,
                    enablePlaceholders = PAGE_ENABLE_PLACEHOLDERS
                ),
                pagingSourceFactory = { PopularPagingSource(popularDataSource) }
            ).flow.map { pagingData ->
                pagingData.map { popularItemDTO ->
                    popularItemMapper.map(popularItemDTO)!!
                }
            }
        }
    }
}