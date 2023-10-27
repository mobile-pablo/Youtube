package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.LocalizedDTO
import com.mobile.pablo.domain.model.Localized
import javax.inject.Inject

class LocalizedMapper @Inject constructor() {

    fun map(dto: LocalizedDTO?): Localized? = dto?.run {
        Localized(
            title = title,
            description = description
        )
    }
}