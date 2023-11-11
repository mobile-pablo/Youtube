package com.mobile.pablo.networking.mapper.common

import com.mobile.pablo.core.model.common.PageInfoDTO
import com.mobile.pablo.networking.model.common.PageInfoResponse
import javax.inject.Inject

internal class PageInfoResponseMapper @Inject constructor() {

    fun map(response: PageInfoResponse?): PageInfoDTO? {
        return response?.run {
            PageInfoDTO(
                totalResults = totalResults,
                resultsPerPage = resultsPerPage
            )
        }
    }
}