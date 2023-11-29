package com.mobile.pablo.networking.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class StatisticsResponse(
    @Json(name = "viewCount") val viewCount: Int? = null,
    @Json(name = "likeCount") val likeCount: Int? = null,
    @Json(name = "dislikeCount") val dislikeCount: Int? = null,
    @Json(name = "favoriteCount") val favoriteCount: Int? = null,
    @Json(name = "commentCount") val commentCount: Int? = null
)
