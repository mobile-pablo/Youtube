package com.mobile.pablo.networking.model.popular

import com.mobile.pablo.networking.model.common.PageInfoResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PopularResponse(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String? = null,
    @Json(name = "nextPageToken") val nextPageToken: String? = null,
    @Json(name = "prevPageToken") val prevPageToken: String? = null,
    @Json(name = "pageInfo") val pageInfo: PageInfoResponse? = null,
    @Json(name = "items") val items: List<PopularItemResponse?>? = null
)