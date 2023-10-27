package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.models.LocalizedDTO
import com.mobile.pablo.networking.model.LocalizedResponse
import javax.inject.Inject

internal class LocalizedResponseMapper @Inject constructor() {

    fun map(response: LocalizedResponse?): LocalizedDTO? = response?.run {
        LocalizedDTO(
            title = title,
            description = description
        )
    }
}