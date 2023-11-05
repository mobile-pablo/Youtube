package com.mobile.pablo.storage.mapper.popular

import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.storage.database.entity.popular.PopularEntity
import com.mobile.pablo.storage.database.entity.popular.PopularWithItemEntity
import com.mobile.pablo.storage.mapper.common.PageInfoEntityMapper
import javax.inject.Inject

internal class PopularEntityMapper @Inject constructor(
    private val pageInfoMapper: PageInfoEntityMapper,
    private val popularItemMapper: PopularItemEntityMapper
) {

    fun map(dto: PopularDTO?): PopularWithItemEntity? {
        return dto?.run {
            PopularWithItemEntity(
                popular = PopularEntity(
                    kind = kind,
                    etag = etag,
                    nextPageToken = nextPageToken,
                    prevPageToken = prevPageToken,
                    pageInfo = pageInfoMapper.map(pageInfo)
                ),
                items = items!!.map {
                    popularItemMapper.map(it, etag)
                },
            )
        }
    }

    fun map(entity: PopularWithItemEntity?): PopularDTO? {
        return entity?.run {
            PopularDTO(
                kind = popular!!.kind,
                etag = popular.etag,
                items = items!!.map(popularItemMapper::map),
                nextPageToken = popular.nextPageToken,
                prevPageToken = popular.prevPageToken,
                pageInfo = pageInfoMapper.map(popular.pageInfo)
            )
        }
    }
}