package com.mobile.pablo.core.model

data class PopularVideosDTO(
    var kind: String? = null,
    var etag: String? = null,
    var nextPageToken: String? = null,
    var pageInfo: PageInfoDTO? = null,
    var items: List<PopularVideoItemDTO?>? = null
)