package com.mobile.pablo.domain.model.popular

import com.mobile.pablo.domain.model.common.ContentDetails
import com.mobile.pablo.domain.model.common.Statistics

data class PopularItem(
    val kind: String? = null,
    val etag: String,
    val id: String? = null,
    val snippet: PopularSnippet? = null,
    val contentDetails: ContentDetails? = null,
    val statistics: Statistics? = null
)