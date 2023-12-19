package com.mobile.pablo.domain.mapper.common

import com.mobile.pablo.core.model.common.IdDTO
import com.mobile.pablo.domain.model.common.Id
import javax.inject.Inject

class IdMapper @Inject constructor() {

    fun map(dto: IdDTO?): Id? {
        return dto?.run {
            Id(
                kind = kind,
                videoId = videoId
            )
        }
    }
}
