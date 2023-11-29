package com.mobile.pablo.networking.source.popular

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.data.callSafe
import com.mobile.pablo.core.ext.map
import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.networking.mapper.popular.PopularResponseMapper
import com.mobile.pablo.networking.service.YoutubeService
import javax.inject.Inject

internal class PopularDataSourceImpl
    @Inject
    constructor(
        private val youtubeService: YoutubeService,
        private val popularResponseMapper: PopularResponseMapper
    ) : PopularDataSource {
        override suspend fun getPopularVideos(
            regionCode: String,
            pageToken: String?
        ): DataTransfer<PopularDTO> {
            val popularResponse =
                callSafe {
                    youtubeService.getPopularSearchVideos(
                        regionCode = regionCode,
                        pageToken = pageToken
                    )
                }

            return popularResponse.map(popularResponseMapper::map)
        }
    }
