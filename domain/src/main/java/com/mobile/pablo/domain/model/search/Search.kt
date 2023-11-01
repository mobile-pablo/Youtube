package com.mobile.pablo.domain.model.search

import com.mobile.pablo.domain.model.common.PageInfo

data class Search(
    var kind: String? = null,
    var etag: String? = null,
    var nextPageToken: String? = null,
    var regionCode: String? = null,
    var pageInfo: PageInfo? = null,
    var items: List<SearchItem?>? = null
)