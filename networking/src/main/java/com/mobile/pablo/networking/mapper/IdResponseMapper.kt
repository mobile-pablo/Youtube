package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.model.IdDTO
import com.mobile.pablo.networking.model.IdResponse
import javax.inject.Inject

internal class IdResponseMapper @Inject constructor() {

    fun map(remote: IdResponse?): IdDTO? {
        return remote?.run {
            IdDTO(
                kind = kind,
                videoId = videoId
            )
        }
    }
}