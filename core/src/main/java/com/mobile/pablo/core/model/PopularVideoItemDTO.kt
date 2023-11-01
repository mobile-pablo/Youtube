package com.mobile.pablo.core.model

import com.squareup.moshi.Json

data class PopularVideoItemDTO(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String? = null,
    @Json(name = "id") val id: String? = null,
    @Json(name = "snippet") val snippet: PopularVideoSnippetDTO? = null
)