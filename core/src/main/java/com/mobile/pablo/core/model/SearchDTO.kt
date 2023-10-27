package com.mobile.pablo.core.model

data class SearchDTO(
    var kind: String? = null,
    var etag: String? = null,
    var nextPageToken: String? = null,
    var regionCode: String? = null,
    var pageInfo: PageInfoDTO? = null,
    var items: List<SearchItemDTO?>? = null
)