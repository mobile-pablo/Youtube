package com.mobile.pablo.domain.mapper.common

import com.mobile.pablo.core.model.common.PageInfoDTO
import com.mobile.pablo.domain.model.common.PageInfo
import javax.inject.Inject

class PageInfoMapper @Inject constructor() {

    fun map(dto: PageInfoDTO?): PageInfo? {
        return dto?.run {
            PageInfo(
                totalResults = totalResults,
                resultsPerPage = resultsPerPage
            )
        }
    }
}