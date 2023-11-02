package com.mobile.pablo.domain.usecase

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.domain.mapper.popular.PopularItemMapper
import com.mobile.pablo.domain.mapper.popular.PopularMapper
import com.mobile.pablo.domain.mapper.search.SearchItemMapper
import com.mobile.pablo.domain.mapper.search.SearchMapper
import com.mobile.pablo.domain.model.popular.Popular
import com.mobile.pablo.domain.model.search.Search
import com.mobile.pablo.networking.source.popular.PopularDataSource
import com.mobile.pablo.networking.source.search.SearchDataSource
import com.mobile.pablo.storage.source.popular.PopularDataStorage
import com.mobile.pablo.storage.source.search.SearchDataStorage
import javax.inject.Inject

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
                    val items = searchResponse.data!!.items!!
                    if (items.isNullOrEmpty()) {
                        val searchLocal = searchDataStorage.getSearchItems()
                        val searchLocalDTO = searchLocal!!.map(searchItemMapper::map)
                        DataTransfer(data = Search(items = searchLocalDTO))
                    } else {
                        val search = searchMapper.map(searchResponse.data)
                        searchDataStorage.insertSearchItems(searchResponse.data!!.items!!)
                        DataTransfer(data = search)
                    }
                }

                else -> DataTransfer(error = searchResponse.error)
            }
        }
    }

    class GetPopularVideos @Inject constructor(
        private val popularDataSource: PopularDataSource,
        private val popularDataStorage: PopularDataStorage,
        private val popularMapper: PopularMapper,
        private val popularItemMapper: PopularItemMapper
    ) : VideosUseCase() {

        suspend operator fun invoke(regionCode: String): DataTransfer<Popular> {
            val popularResponse = popularDataSource.getPopularVideos(regionCode = regionCode)

            return when {
                popularResponse.isSuccessful -> {
                    val items = popularResponse.data!!.items!!
                    if (items.isNullOrEmpty()) {
                        val popularLocal = popularDataStorage.getPopularItems()
                        val popularLocalDTO = popularLocal!!.map(popularItemMapper::map)
                        DataTransfer(data = Popular(items = popularLocalDTO))
                    } else {
                        val popular = popularMapper.map(popularResponse.data)
                        popularDataStorage.insertPopularItems(popularResponse.data!!.items!!)
                        DataTransfer(data = popular)
                    }
                }

                else -> DataTransfer(error = popularResponse.error)
            }
        }
    }
}