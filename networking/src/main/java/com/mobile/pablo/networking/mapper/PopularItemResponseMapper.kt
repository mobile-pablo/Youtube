package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.model.PopularItemDTO
import com.mobile.pablo.networking.model.PopularItemResponse
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