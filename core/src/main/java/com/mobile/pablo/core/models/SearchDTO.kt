package com.mobile.pablo.core.models

data class SearchDTO(
    val kind: String? = null,
    val etag: String? = null,
    val nextPageToken: String? = null,
    val regionCode: String? = null,
    val pageInfo: PageInfoDTO? = null,
    val items: List<SearchItemDTO?>? = null
)