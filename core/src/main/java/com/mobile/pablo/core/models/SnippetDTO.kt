package com.mobile.pablo.core.models

import com.squareup.moshi.Json

data class SnippetDTO(
    var publishedAt: String? = null,
    var channelId: String? = null,
    var title: String? = null,
    var description: String? = null,
    var thumbnails: ThumbnailsDTO? = null,
    var channelTitle: String? = null,
    var liveBroadcastContent: String? = null,
    var publishTime: String? = null
)
