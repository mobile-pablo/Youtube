package com.mobile.pablo.networking.source

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.model.PopularDTO
import com.mobile.pablo.core.model.SearchDTO
import com.mobile.pablo.networking.mapper.PopularResponseMapper
import com.mobile.pablo.networking.mapper.SearchResponseMapper
import com.mobile.pablo.networking.service.YoutubeService
import javax.inject.Inject

internal class VideoDataSourceImpl @Inject constructor(
    private val youtubeService: YoutubeService,
    private val searchResponseMapper: SearchResponseMapper,
    private val popularResponseMapper: PopularResponseMapper
) : VideoDataSource {

    override suspend fun getSearchVideos(query: String): DataTransfer<SearchDTO> {
        val searchResponse = youtubeService.getSearchVideos(q = query)

        return when {
            searchResponse.isSuccessful -> {
                val searchResult = searchResponse.body()
                DataTransfer(searchResponseMapper.map(searchResult))
            }

            else -> {
                DataTransfer(error = Exception(searchResponse.message()))
            }
        }
    }

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
                DataTransfer(error = Exception(searchPopularResponse.code().toString()))
            }
        }
    }
}