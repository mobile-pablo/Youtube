package com.mobile.pablo.storage.mapper.popular

import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.storage.database.entity.popular.PopularEntity
import com.mobile.pablo.storage.mapper.common.PageInfoEntityMapper
import javax.inject.Inject

internal class PopularEntityMapper @Inject constructor(
    private val pageInfoMapper: PageInfoEntityMapper,
    private val popularItemMapper: PopularItemEntityMapper
) {

    fun map(dto: PopularDTO?): PopularEntity? {
        return dto?.run {
            PopularEntity(
                kind = kind,
                etag = etag,
                items = items!!.map(popularItemMapper::map),
                nextPageToken = nextPageToken,
                prevPageToken = prevPageToken,
                pageInfo = pageInfoMapper.map(pageInfo)
            )
        }
    }

    fun map(entity: PopularEntity?): PopularDTO? {
        return entity?.run {
            PopularDTO(
                kind = kind,
                etag = etag,
                items = items.map(popularItemMapper::map),
                nextPageToken = nextPageToken,
                prevPageToken = prevPageToken,
                pageInfo = pageInfoMapper.map(pageInfo)
            )
        }
    }
}