package com.mobile.pablo.networking.mapper.popular

import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.networking.mapper.common.PageInfoResponseMapper
import com.mobile.pablo.networking.model.popular.PopularResponse
import javax.inject.Inject

internal class PopularResponseMapper @Inject constructor(
    private val pageInfoResponseMapper: PageInfoResponseMapper,
    private val popularItemResponseMapper: PopularItemResponseMapper
) {

    fun map(response: PopularResponse): PopularDTO {
        return response.run {
            PopularDTO(
                kind = kind,
                etag = etag,
                items = items?.map(popularItemResponseMapper::map),
                nextPageToken = nextPageToken,
                prevPageToken = prevPageToken,
                pageInfo = pageInfoResponseMapper.map(pageInfo)
            )
        }
    }
}
