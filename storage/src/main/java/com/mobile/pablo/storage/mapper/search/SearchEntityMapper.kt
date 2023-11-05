package com.mobile.pablo.storage.mapper.search

import com.mobile.pablo.core.model.search.SearchDTO
import com.mobile.pablo.storage.database.entity.search.SearchEntity
import com.mobile.pablo.storage.mapper.common.PageInfoEntityMapper
import javax.inject.Inject

internal class SearchEntityMapper @Inject constructor(
    private val pageInfoMapper: PageInfoEntityMapper,
    private val searchItemMapper: SearchItemEntityMapper
) {

    fun map(dto: SearchDTO?): SearchEntity? {
        return dto?.run {
            SearchEntity(
                kind = kind,
                etag = etag,
                nextPageToken = nextPageToken,
                regionCode = regionCode,
                pageInfo = pageInfoMapper.map(pageInfo),
                items = items!!.map(searchItemMapper::map)
            )
        }
    }

    fun map(entity: SearchEntity?): SearchDTO? {
        return entity?.run {
            SearchDTO(
                kind = kind,
                etag = etag,
                nextPageToken = nextPageToken,
                regionCode = regionCode,
                pageInfo = pageInfoMapper.map(pageInfo),
                items = items.map(searchItemMapper::map)
            )
        }
    }
}