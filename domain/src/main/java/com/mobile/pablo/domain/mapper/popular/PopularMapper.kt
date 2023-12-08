package com.mobile.pablo.domain.mapper.popular

import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.domain.mapper.common.PageInfoMapper
import com.mobile.pablo.domain.model.popular.Popular
import javax.inject.Inject

internal class PopularMapper @Inject constructor(
    private val pageInfoMapper: PageInfoMapper,
    private val popularItemMapper: PopularItemMapper
) {

    fun map(dto: PopularDTO?): Popular? {
        return dto?.run {
            Popular(
                kind = kind,
                etag = etag,
                items = items!!.map(popularItemMapper::map),
                nextPageToken = nextPageToken,
                prevPageToken = prevPageToken,
                pageInfo = pageInfoMapper.map(pageInfo)
            )
        }
    }
}
