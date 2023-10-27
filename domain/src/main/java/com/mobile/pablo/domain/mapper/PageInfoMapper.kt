package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.PageInfoDTO
import com.mobile.pablo.domain.model.PageInfo
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