package com.mobile.pablo.networking.mapper.popular

import com.mobile.pablo.core.model.popular.PopularItemDTO
import com.mobile.pablo.networking.model.popular.PopularItemResponse
import javax.inject.Inject

internal class PopularItemResponseMapper @Inject constructor(
    private val popularSnippetResponseMapper: PopularSnippetResponseMapper
) {

    fun map(response: PopularItemResponse?): PopularItemDTO? {
        return response?.run {
            PopularItemDTO(
                kind = kind,
                etag = etag,
                id = id,
                snippet = popularSnippetResponseMapper.map(snippet)
            )
        }
    }
}