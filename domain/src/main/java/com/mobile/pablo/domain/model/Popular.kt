package com.mobile.pablo.domain.model

data class Popular(
    var kind: String? = null,
    var etag: String? = null,
    var nextPageToken: String? = null,
    var pageInfo: PageInfo? = null,
    var items: List<PopularItem?>? = null
)