package com.mobile.pablo.core.model.search

import com.mobile.pablo.core.model.common.PageInfoDTO

data class SearchDTO(
    var kind: String? = null,
    var etag: String,
    var nextPageToken: String? = null,
    var prevPageToken: String? = null,
    var regionCode: String? = null,
    var pageInfo: PageInfoDTO? = null,
    var items: List<SearchItemDTO?>? = null
)
