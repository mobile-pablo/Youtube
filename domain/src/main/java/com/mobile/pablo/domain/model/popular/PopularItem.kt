package com.mobile.pablo.domain.model.popular

import com.squareup.moshi.Json

data class PopularItem(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String,
    @Json(name = "id") val id: String? = null,
    @Json(name = "snippet") val snippet: PopularSnippet? = null
)