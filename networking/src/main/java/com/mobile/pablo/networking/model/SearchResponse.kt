package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String? = null,
    @Json(name = "nextPageToken") val nextPageToken: String? = null,
    @Json(name = "regionCode") val regionCode: String? = null,
    @Json(name = "pageInfo") val pageInfo: PageInfoResponse? = null,
    @Json(name = "items") val items: ArrayList<SearchItemResponse> = arrayListOf()
)