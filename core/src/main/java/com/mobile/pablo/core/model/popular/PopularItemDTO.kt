package com.mobile.pablo.core.model.popular

import com.mobile.pablo.core.model.common.ContentDetailsDTO
import com.mobile.pablo.core.model.common.StatisticsDTO

data class PopularItemDTO(
    val kind: String? = null,
    val etag: String,
    val id: String? = null,
    val snippet: PopularSnippetDTO? = null,
    val contentDetails: ContentDetailsDTO? = null,
    val statistics: StatisticsDTO? = null
)