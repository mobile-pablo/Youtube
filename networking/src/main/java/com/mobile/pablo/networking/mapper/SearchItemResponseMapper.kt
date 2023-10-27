package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.models.SearchItemDTO
import com.mobile.pablo.networking.model.SearchItemResponse
import javax.inject.Inject

internal class SearchItemResponseMapper @Inject constructor(
    private val idResponseMapper : IdResponseMapper,
    private val snippetResponseMapper: SnippetResponseMapper
) {
    fun map(remote: SearchItemResponse?): SearchItemDTO? {
        return remote?.run {
            SearchItemDTO(
                kind,
                etag,
                idResponseMapper.map(id),
                snippetResponseMapper.map(snippet)
            )
        }
    }
}