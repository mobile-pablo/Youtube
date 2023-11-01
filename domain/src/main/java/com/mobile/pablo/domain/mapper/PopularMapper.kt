package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.PopularDTO
import com.mobile.pablo.domain.model.Popular
import javax.inject.Inject

class PopularMapper @Inject constructor(
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
                pageInfo = pageInfoMapper.map(pageInfo)
            )
        }
    }
}