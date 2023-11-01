package com.mobile.pablo.domain.usecase

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.domain.mapper.popular.PopularMapper
import com.mobile.pablo.domain.mapper.search.SearchMapper
import com.mobile.pablo.domain.model.popular.Popular
import com.mobile.pablo.domain.model.search.Search
import com.mobile.pablo.networking.source.VideoDataSource
import javax.inject.Inject

sealed class VideosUseCase {

    class GetSearchVideos @Inject constructor(
        private val videoDataSource: VideoDataSource,
        private val searchMapper: SearchMapper
    ) : VideosUseCase() {

        suspend operator fun invoke(query: String): DataTransfer<Search> {
            val searchResponse = videoDataSource.getSearchVideos(query)

            return when {
                searchResponse.isSuccessful -> {
                    val search = searchMapper.map(searchResponse.data)
                    DataTransfer(data = search)
                }

                else -> DataTransfer(error = searchResponse.error)
            }
        }
    }

    class GetPopularVideos @Inject constructor(
        private val videoDataSource: VideoDataSource,
        private val popularMapper: PopularMapper
    ) : VideosUseCase() {

        suspend operator fun invoke(regionCode: String): DataTransfer<Popular> {
            val searchResponse = videoDataSource.getPopularVideos(regionCode = regionCode)

            return when {
                searchResponse.isSuccessful -> {
                    val search = popularMapper.map(searchResponse.data)
                    DataTransfer(data = search)
                }

                else -> DataTransfer(error = searchResponse.error)
            }
        }
    }
}