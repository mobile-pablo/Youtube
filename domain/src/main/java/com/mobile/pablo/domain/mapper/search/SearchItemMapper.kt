package com.mobile.pablo.domain.mapper.search

import com.mobile.pablo.core.model.search.SearchItemDTO
import com.mobile.pablo.domain.mapper.common.IdMapper
import com.mobile.pablo.domain.model.search.SearchItem
import javax.inject.Inject

class SearchItemMapper @Inject constructor(
    private val idMapper: IdMapper,
    private val searchSnippetMapper: SearchSnippetMapper
) {

    fun map(dto: SearchItemDTO?): SearchItem? {
        return dto?.run {
            SearchItem(
                kind = kind,
                etag = etag,
                id = idMapper.map(id),
                snippet = searchSnippetMapper.map(snippet)
            )
        }
    }
}