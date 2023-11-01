package com.mobile.pablo.storage.mapper

import com.mobile.pablo.core.model.IdDTO
import com.mobile.pablo.storage.database.entity.search.IdEntity
import javax.inject.Inject

internal class IdEntityMapper @Inject constructor() {

    fun map(dto: IdDTO?): IdEntity? {
        return dto?.run {
            IdEntity(
                kind = kind,
                videoId = videoId
            )
        }
    }

    fun map(entity: IdEntity?): IdDTO? {
        return entity?.run {
            IdDTO(
                kind = kind,
                videoId = videoId
            )
        }
    }
}