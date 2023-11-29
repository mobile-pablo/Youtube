package com.mobile.pablo.networking.mapper.common

import com.mobile.pablo.core.model.common.StatisticsDTO
import com.mobile.pablo.networking.model.common.StatisticsResponse
import javax.inject.Inject

internal class StatisticsResponseMapper
    @Inject
    constructor() {
        fun map(response: StatisticsResponse?): StatisticsDTO? {
            return response?.run {
                StatisticsDTO(
                    viewCount = viewCount,
                    likeCount = likeCount,
                    dislikeCount = dislikeCount,
                    favoriteCount = favoriteCount,
                    commentCount = commentCount
                )
            }
        }
    }
