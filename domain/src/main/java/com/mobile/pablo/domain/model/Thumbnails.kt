package com.mobile.pablo.domain.model

data class Thumbnails(
    val default: Thumbnail? = null,
    val medium: Thumbnail? = null,
    val high: Thumbnail? = null
)

data class Thumbnail(
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
)