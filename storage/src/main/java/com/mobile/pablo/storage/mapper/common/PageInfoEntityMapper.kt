package com.mobile.pablo.storage.mapper.common

import com.mobile.pablo.core.model.common.PageInfoDTO
import com.mobile.pablo.storage.database.entity.common.PageInfoEntity
import javax.inject.Inject

internal class PageInfoEntityMapper @Inject constructor() {

    fun map(dto: PageInfoDTO?): PageInfoEntity? {
        return dto?.run {
            PageInfoEntity(
                totalResults = totalResults,
                resultsPerPage = resultsPerPage
            )
        }
    }

    fun map(entity: PageInfoEntity?): PageInfoDTO? {
        return entity?.run {
            PageInfoDTO(
                totalResults = totalResults,
                resultsPerPage = resultsPerPage
            )
        }
    }
}