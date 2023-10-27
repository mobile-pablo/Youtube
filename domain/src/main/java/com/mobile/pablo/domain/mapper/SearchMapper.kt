package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.SearchDTO
import com.mobile.pablo.domain.model.Search
import javax.inject.Inject

internal class SearchMapper @Inject constructor(
    private val pageInfoMapper: PageInfoMapper,
    private val searchItemMapper: SearchItemMapper
) {

    fun mapSearch(dto: SearchDTO?): Search? {
        return dto?.run {
            Search(
                kind = kind,
                etag = etag,
                nextPageToken = nextPageToken,
                regionCode = regionCode,
                pageInfo = pageInfoMapper.map(pageInfo),
                items = items!!.map(searchItemMapper::mapSearch)
            )
        }
    }

    fun mapPopularSearch(dto: SearchDTO?): Search? {
        return dto?.run {
            Search(
                kind = kind,
                etag = etag,
                items = items!!.map(searchItemMapper::mapPopularSearch),
                nextPageToken = nextPageToken,
                pageInfo = pageInfoMapper.map(pageInfo),
            )
        }
    }
}