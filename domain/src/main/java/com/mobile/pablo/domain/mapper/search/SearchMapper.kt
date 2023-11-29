package com.mobile.pablo.domain.mapper.search

import com.mobile.pablo.core.model.search.SearchDTO
import com.mobile.pablo.domain.mapper.common.PageInfoMapper
import com.mobile.pablo.domain.model.search.Search
import javax.inject.Inject

class SearchMapper
    @Inject
    constructor(
        private val pageInfoMapper: PageInfoMapper,
        private val searchItemMapper: SearchItemMapper
    ) {
        fun map(dto: SearchDTO?): Search? {
            return dto?.run {
                Search(
                    kind = kind,
                    etag = etag,
                    nextPageToken = nextPageToken,
                    regionCode = regionCode,
                    pageInfo = pageInfoMapper.map(pageInfo),
                    items = items!!.map(searchItemMapper::map)
                )
            }
        }
    }
