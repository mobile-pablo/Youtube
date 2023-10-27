package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.model.LocalizedDTO
import com.mobile.pablo.networking.model.LocalizedResponse
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