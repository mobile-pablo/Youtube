package com.mobile.pablo.domain.model

data class PopularVideos(
    var kind: String? = null,
    var etag: String? = null,
    var nextPageToken: String? = null,
    var pageInfo: PageInfo? = null,
    var items: List<PopularVideoItem?>? = null
)