package com.mobile.pablo.core.model.popular

import com.mobile.pablo.core.model.common.PageInfoDTO

data class PopularDTO(
    var kind: String? = null,
    var etag: String,
    var nextPageToken: String? = null,
    var prevPageToken: String? = null,
    var pageInfo: PageInfoDTO? = null,
    var items: List<PopularItemDTO?>? = null
)
