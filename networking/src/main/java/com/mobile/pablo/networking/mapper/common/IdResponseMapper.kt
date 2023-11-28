package com.mobile.pablo.networking.mapper.common

import com.mobile.pablo.core.model.common.IdDTO
import com.mobile.pablo.networking.model.common.IdResponse
import javax.inject.Inject

internal class IdResponseMapper
    @Inject
    constructor() {
        fun map(remote: IdResponse?): IdDTO? {
            return remote?.run {
                IdDTO(
                    kind = kind,
                    videoId = videoId
                )
            }
        }
    }
