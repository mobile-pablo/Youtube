package com.mobile.pablo.storage.mapper.search

import com.mobile.pablo.core.model.search.SearchDTO
import com.mobile.pablo.storage.database.entity.search.SearchEntity
import com.mobile.pablo.storage.database.entity.search.SearchWithItemEntity
import com.mobile.pablo.storage.mapper.common.PageInfoEntityMapper
import javax.inject.Inject

internal class SearchEntityMapper @Inject constructor(
    private val pageInfoMapper: PageInfoEntityMapper,
    private val searchItemMapper: SearchItemEntityMapper
) {

    fun map(dto: SearchDTO?): SearchWithItemEntity? {
        return dto?.run {
            SearchWithItemEntity(
                search = SearchEntity(
                    kind = kind,
                    etag = etag,
                    nextPageToken = nextPageToken,
                    prevPageToken = prevPageToken,
                    pageInfo = pageInfoMapper.map(pageInfo)
                ),
                items = items!!.map {
                    searchItemMapper.map(it, etag)
                }
            )
        }
    }

    fun map(entity: SearchWithItemEntity?): SearchDTO? {
        return entity?.run {
            SearchDTO(
                kind = search!!.kind,
                etag = search.etag,
                nextPageToken = search.nextPageToken,
                prevPageToken = search.prevPageToken,
                regionCode = search.regionCode,
                pageInfo = pageInfoMapper.map(search.pageInfo),
                items = items!!.map(searchItemMapper::map)
            )
        }
    }
}