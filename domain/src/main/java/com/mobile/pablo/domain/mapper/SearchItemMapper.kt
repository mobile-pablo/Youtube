package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.PopularVideoItemDTO
import com.mobile.pablo.core.model.SearchItemDTO
import com.mobile.pablo.domain.model.PopularVideoItem
import com.mobile.pablo.domain.model.SearchItem
import javax.inject.Inject

class SearchItemMapper @Inject constructor(
    private val idMapper: IdMapper,
    private val snippetMapper: SnippetMapper
) {

    fun mapSearch(dto: SearchItemDTO?): SearchItem? {
        return dto?.run {
            SearchItem(
                kind = kind,
                etag = etag,
                id = idMapper.map(id),
                snippet = snippetMapper.mapSearch(snippet)
            )
        }
    }

    fun mapPopularSearch(dto: PopularVideoItemDTO?): PopularVideoItem? {
        return dto?.run {
            PopularVideoItem(
                kind = kind,
                etag = etag,
                id = id,
                snippet = snippetMapper.mapPopularSearch(snippet)
            )
        }
    }
}