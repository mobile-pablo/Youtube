package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.models.PageInfoDTO
import com.mobile.pablo.networking.model.PageInfoResponse
import javax.inject.Inject

internal class PageInfoResponseMapper @Inject constructor() {
    fun map(remote: PageInfoResponse?): PageInfoDTO? {
        return remote?.run {
            PageInfoDTO(
                totalResults,
                resultsPerPage
            )
        }
    }
}