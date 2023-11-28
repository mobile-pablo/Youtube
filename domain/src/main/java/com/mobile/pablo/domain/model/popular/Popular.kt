package com.mobile.pablo.domain.model.popular

import com.mobile.pablo.domain.model.common.PageInfo

data class Popular(
    var kind: String? = null,
    var etag: String,
    var nextPageToken: String? = null,
    var prevPageToken: String? = null,
    var pageInfo: PageInfo? = null,
    var items: List<PopularItem?>? = null
)
