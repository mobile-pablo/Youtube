package com.mobile.pablo.domain.model

data class SearchItem(
    val kind: String? = null,
    val etag: String,
    val id: Id? = null,
    val snippet: SearchSnippet? = null
)