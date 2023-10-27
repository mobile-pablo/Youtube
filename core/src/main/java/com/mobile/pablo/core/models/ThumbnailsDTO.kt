package com.mobile.pablo.core.models

data class ThumbnailsDTO(
    var default: ThumbnailDTO? = null,
    var medium: ThumbnailDTO? = null,
    var high: ThumbnailDTO? = null
)

data class ThumbnailDTO(
    var url: String? = null,
    var width: Int? = null,
    var height: Int? = null
)
