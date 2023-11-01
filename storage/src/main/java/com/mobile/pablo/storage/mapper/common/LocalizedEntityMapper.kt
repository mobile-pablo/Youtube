package com.mobile.pablo.storage.mapper.common

import com.mobile.pablo.core.model.common.LocalizedDTO
import com.mobile.pablo.storage.database.entity.common.LocalizedEntity
import javax.inject.Inject

internal class LocalizedEntityMapper @Inject constructor() {

    fun map(dto: LocalizedDTO?): LocalizedEntity? = dto?.run {
        LocalizedEntity(
            title = title,
            description = description
        )
    }

    fun map(entity: LocalizedEntity?): LocalizedDTO? = entity?.run {
        LocalizedDTO(
            title = title,
            description = description
        )
    }
}