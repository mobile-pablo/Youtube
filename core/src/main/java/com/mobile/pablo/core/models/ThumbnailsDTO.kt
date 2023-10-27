package com.mobile.pablo.core.models

data class ThumbnailsDTO(
    val default: ThumbnailDTO? = null,
    val medium: ThumbnailDTO? = null,
    val high: ThumbnailDTO? = null
)

data class ThumbnailDTO(
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
)