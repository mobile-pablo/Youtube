package com.mobile.pablo.core.model

import com.squareup.moshi.Json

data class PopularItemDTO(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String,
    @Json(name = "id") val id: String? = null,
    @Json(name = "snippet") val snippet: PopularSnippetDTO? = null
)