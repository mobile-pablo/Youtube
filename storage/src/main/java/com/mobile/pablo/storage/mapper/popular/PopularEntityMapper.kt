package com.mobile.pablo.storage.mapper.popular

import com.mobile.pablo.core.model.popular.PopularItemDTO
import com.mobile.pablo.storage.database.entity.popular.PopularItemEntity
import javax.inject.Inject

internal class PopularEntityMapper @Inject constructor(
    private val popularSnippetEntityMapper: PopularSnippetEntityMapper
) {

    fun map(dto: PopularItemDTO?): PopularItemEntity? {
        return dto?.run {
            PopularItemEntity(
                kind = kind,
                etag = etag,
                id = id,
                snippet = popularSnippetEntityMapper.map(snippet)
            )
        }
    }

    fun map(entity: PopularItemEntity?): PopularItemDTO? {
        return entity?.run {
            PopularItemDTO(
                kind = kind,
                etag = etag,
                id = id,
                snippet = popularSnippetEntityMapper.map(snippet)
            )
        }
    }
}