package com.mobile.pablo.core.model.search

import com.mobile.pablo.core.model.common.IdDTO

data class SearchItemDTO(
    val kind: String? = null,
    val etag: String,
    val id: IdDTO? = null,
    val snippet: SearchSnippetDTO? = null
)