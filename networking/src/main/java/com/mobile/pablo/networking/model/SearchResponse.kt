package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "kind") var kind: String? = null,
    @Json(name = "etag") var etag: String? = null,
    @Json(name = "nextPageToken") var nextPageToken: String? = null,
    @Json(name = "regionCode") var regionCode: String? = null,
    @Json(name = "pageInfo") var pageInfo: PageInfoResponse? = null,
    @Json(name = "items") var items: ArrayList<SearchItemResponse> = arrayListOf()
)