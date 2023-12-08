package com.mobile.pablo.storage.mapper.common

import com.mobile.pablo.core.model.common.IdDTO
import com.mobile.pablo.storage.database.entity.common.IdEntity
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
