package com.mobile.pablo.domain.mapper.common

import com.mobile.pablo.core.model.common.StatisticsDTO
import com.mobile.pablo.domain.model.common.Statistics
import javax.inject.Inject

class StatisticsMapper @Inject constructor() {

    fun map(dto : StatisticsDTO?) : Statistics? {
        return dto?.run {
            Statistics(
                viewCount = viewCount,
                likeCount = likeCount,
                dislikeCount = dislikeCount,
                favoriteCount = favoriteCount,
                commentCount = commentCount
            )
        }
    }
}