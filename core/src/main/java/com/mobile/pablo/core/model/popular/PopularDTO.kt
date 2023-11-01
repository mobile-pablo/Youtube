package com.mobile.pablo.core.model.popular

import com.mobile.pablo.core.model.common.PageInfoDTO

data class PopularDTO(
    var kind: String? = null,
    var etag: String? = null,
    var nextPageToken: String? = null,
    var pageInfo: PageInfoDTO? = null,
    var items: List<PopularItemDTO?>? = null
)