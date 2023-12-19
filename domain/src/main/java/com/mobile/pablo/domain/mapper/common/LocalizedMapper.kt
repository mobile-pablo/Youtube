package com.mobile.pablo.domain.mapper.common

import com.mobile.pablo.core.model.common.LocalizedDTO
import com.mobile.pablo.domain.model.common.Localized
import javax.inject.Inject

class LocalizedMapper @Inject constructor() {

    fun map(dto: LocalizedDTO?): Localized? =
        dto?.run {
            Localized(
                title = title,
                description = description
            )
        }
}
