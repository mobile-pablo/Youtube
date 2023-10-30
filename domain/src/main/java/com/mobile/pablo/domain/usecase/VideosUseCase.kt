package com.mobile.pablo.domain.usecase

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.domain.mapper.SearchMapper
import com.mobile.pablo.domain.model.Search
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
                    val search = searchMapper.mapSearch(searchResponse.data)
                    DataTransfer(data = search)
                }

                else -> DataTransfer(error = searchResponse.error)
            }
        }
    }

    class GetPopularVideos @Inject constructor(
        private val videoDataSource: VideoDataSource,
        private val searchMapper: SearchMapper
    ) : VideosUseCase() {

        suspend operator fun invoke(regionCode: String): DataTransfer<Search> {
            val searchResponse = videoDataSource.getPopularVideos(regionCode = regionCode)

            return when {
                searchResponse.isSuccessful -> {
                    val search = searchMapper.mapPopularSearch(searchResponse.data)
                    DataTransfer(data = search)
                }

                else -> DataTransfer(error = searchResponse.error)
            }
        }
    }
}