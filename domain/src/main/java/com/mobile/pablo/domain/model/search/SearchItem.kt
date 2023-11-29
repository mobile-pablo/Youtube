package com.mobile.pablo.domain.model.search

import com.mobile.pablo.domain.model.common.Id

data class SearchItem(
    val kind: String? = null,
    val etag: String? = null,
    val id: Id? = null,
    val snippet: SearchSnippet? = null
)
