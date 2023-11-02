package com.mobile.pablo.networking.source.popular

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.data.NetworkException
import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.networking.mapper.popular.PopularResponseMapper
import com.mobile.pablo.networking.service.YoutubeService
import javax.inject.Inject

internal class PopularDataSourceImpl @Inject constructor(
    private val youtubeService: YoutubeService,
    private val popularResponseMapper: PopularResponseMapper
) : PopularDataSource {

    override suspend fun getPopularVideos(
        regionCode: String
    ): DataTransfer<PopularDTO> {
        val searchPopularResponse = youtubeService.getPopularSearchVideos(regionCode = regionCode)

        return when {
            searchPopularResponse.isSuccessful -> {
                val searchResult = searchPopularResponse.body()
                DataTransfer(popularResponseMapper.map(searchResult))
            }

            else -> {
                DataTransfer(
                    error = NetworkException(
                        code = searchPopularResponse.code(),
                        message = searchPopularResponse.message()
                    )
                )
            }
        }
    }
}