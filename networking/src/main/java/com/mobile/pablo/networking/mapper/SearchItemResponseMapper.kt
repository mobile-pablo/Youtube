package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.model.PopularVideoItemDTO
import com.mobile.pablo.core.model.SearchItemDTO
import com.mobile.pablo.networking.model.PopularVideoItemResponse
import com.mobile.pablo.networking.model.SearchItemResponse
import javax.inject.Inject

internal class SearchItemResponseMapper @Inject constructor(
    private val idResponseMapper: IdResponseMapper,
    private val snippetResponseMapper: SnippetResponseMapper
) {

    fun mapSearch(response: SearchItemResponse?): SearchItemDTO? {
        return response?.run {
            SearchItemDTO(
                kind = kind,
                etag = etag,
                id = idResponseMapper.map(id),
                snippet = snippetResponseMapper.mapSearch(snippet)
            )
        }
    }

    fun mapPopularSearch(response: PopularVideoItemResponse?): PopularVideoItemDTO? {
        return response?.run {
            PopularVideoItemDTO(
                kind = kind,
                etag = etag,
                id = id,
                snippet = snippetResponseMapper.mapPopularSearch(snippet)
            )
        }
    }
}