package com.mobile.pablo.networking.mapper.common

import com.mobile.pablo.core.model.common.LocalizedDTO
import com.mobile.pablo.networking.model.common.LocalizedResponse
import javax.inject.Inject

internal class LocalizedResponseMapper @Inject constructor() {

    fun map(response: LocalizedResponse?): LocalizedDTO? {
        return response?.run {
            LocalizedDTO(
                title = title,
                description = description
            )
        }
    }
}
