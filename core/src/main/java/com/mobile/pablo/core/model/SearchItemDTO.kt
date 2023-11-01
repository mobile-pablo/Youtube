package com.mobile.pablo.core.model

data class SearchItemDTO(
    val kind: String? = null,
    val etag: String,
    val id: IdDTO? = null,
    val snippet: SearchSnippetDTO? = null
)