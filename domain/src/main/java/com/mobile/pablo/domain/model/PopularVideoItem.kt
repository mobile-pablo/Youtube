package com.mobile.pablo.domain.model

import com.squareup.moshi.Json

data class PopularVideoItem(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String? = null,
    @Json(name = "id") val id: String? = null,
    @Json(name = "snippet") val snippet: PopularVideoSnippet? = null
)