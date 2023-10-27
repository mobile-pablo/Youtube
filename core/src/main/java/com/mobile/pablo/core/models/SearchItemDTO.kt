package com.mobile.pablo.core.models

data class SearchItemDTO(
    var kind: String? = null,
    var etag: String? = null,
    var id: IdDTO? = null,
    var snippet: SnippetDTO? = null
)