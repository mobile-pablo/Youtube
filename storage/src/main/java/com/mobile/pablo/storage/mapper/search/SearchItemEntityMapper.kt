package com.mobile.pablo.storage.mapper.search

import com.mobile.pablo.core.model.search.SearchItemDTO
import com.mobile.pablo.storage.database.entity.search.SearchItemEntity
import com.mobile.pablo.storage.mapper.common.IdEntityMapper
import javax.inject.Inject

internal class SearchItemEntityMapper @Inject constructor(
    private val idEntityMapper: IdEntityMapper,
    private val snippetEntityMapper: SnippetEntityMapper
) {

    fun map(
        dto: SearchItemDTO?,
        parentId: String
    ): SearchItemEntity? {
        return dto?.run {
            SearchItemEntity(
                kind = kind,
                etag = etag,
                parentId = parentId,
                id = idEntityMapper.map(id),
                snippet = snippetEntityMapper.map(snippet)
            )
        }
    }

    fun map(entity: SearchItemEntity?): SearchItemDTO? {
        return entity?.run {
            SearchItemDTO(
                kind = kind,
                etag = etag,
                id = idEntityMapper.map(id),
                snippet = snippetEntityMapper.map(snippet)
            )
        }
    }
}