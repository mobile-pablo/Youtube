package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.models.SearchDTO
import com.mobile.pablo.networking.model.SearchResponse
import javax.inject.Inject

internal class SearchResponseMapper @Inject constructor(
    private val pageInfoResponseMapper: PageInfoResponseMapper,
    private val searchItemResponseMapper: SearchItemResponseMapper
) {

    fun map(remote: SearchResponse?): SearchDTO? {
        return remote?.run {
            SearchDTO(
                kind,
                etag,
                nextPageToken,
                regionCode,
                pageInfoResponseMapper.map(pageInfo),
                items.map(searchItemResponseMapper::map)
            )
        }
    }
}