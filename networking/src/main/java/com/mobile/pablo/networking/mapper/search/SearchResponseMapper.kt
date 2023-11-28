package com.mobile.pablo.networking.mapper.search

import com.mobile.pablo.core.model.search.SearchDTO
import com.mobile.pablo.networking.mapper.common.PageInfoResponseMapper
import com.mobile.pablo.networking.model.search.SearchResponse
import javax.inject.Inject

internal class SearchResponseMapper
    @Inject
    constructor(
        private val pageInfoResponseMapper: PageInfoResponseMapper,
        private val searchItemResponseMapper: SearchItemResponseMapper
    ) {
        fun map(response: SearchResponse): SearchDTO {
            return response.run {
                SearchDTO(
                    kind = kind,
                    etag = etag,
                    nextPageToken = nextPageToken,
                    regionCode = regionCode,
                    pageInfo = pageInfoResponseMapper.map(pageInfo),
                    items = items?.map(searchItemResponseMapper::map)
                )
            }
        }
    }
