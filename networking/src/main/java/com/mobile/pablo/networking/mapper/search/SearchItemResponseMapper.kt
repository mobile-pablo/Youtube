package com.mobile.pablo.networking.mapper.search

import com.mobile.pablo.core.model.search.SearchItemDTO
import com.mobile.pablo.networking.mapper.common.IdResponseMapper
import com.mobile.pablo.networking.model.search.SearchItemResponse
import javax.inject.Inject

internal class SearchItemResponseMapper @Inject constructor(
    private val idResponseMapper: IdResponseMapper,
    private val searchSnippetResponseMapper: SearchSnippetResponseMapper
) {

    fun map(response: SearchItemResponse?): SearchItemDTO? {
        return response?.run {
            SearchItemDTO(
                kind = kind,
                etag = etag,
                id = idResponseMapper.map(id),
                snippet = searchSnippetResponseMapper.map(snippet)
            )
        }
    }
}
