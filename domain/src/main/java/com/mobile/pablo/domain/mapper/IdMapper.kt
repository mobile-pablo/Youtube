package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.IdDTO
import com.mobile.pablo.domain.model.Id
import javax.inject.Inject

internal class IdMapper @Inject constructor() {

    fun map(dto: IdDTO?): Id? {
        return dto?.run {
            Id(
                kind = kind,
                videoId = videoId
            )
        }
    }
}