package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.model.PopularDTO
import com.mobile.pablo.networking.model.PopularResponse
import javax.inject.Inject

internal class PopularResponseMapper @Inject constructor(
    private val pageInfoResponseMapper: PageInfoResponseMapper,
    private val popularItemResponseMapper: PopularItemResponseMapper
) {

    fun map(response: PopularResponse?): PopularDTO? {
        return response?.run {
            PopularDTO(
                kind = kind,
                etag = etag,
                items = items?.map(popularItemResponseMapper::map),
                nextPageToken = nextPageToken,
                pageInfo = pageInfoResponseMapper.map(pageInfo)
            )
        }
    }
}