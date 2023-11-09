package com.mobile.pablo.networking.model.popular

import com.mobile.pablo.networking.model.common.ContentDetailsResponse
import com.mobile.pablo.networking.model.common.StatisticsResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PopularItemResponse(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String,
    @Json(name = "id") val id: String? = null,
    @Json(name = "snippet") val snippet: PopularSnippetResponse? = null,
    @Json(name = "contentDetails") val contentDetails: ContentDetailsResponse? = null,
    @Json(name = "statistics") val statistics: StatisticsResponse? = null,
)