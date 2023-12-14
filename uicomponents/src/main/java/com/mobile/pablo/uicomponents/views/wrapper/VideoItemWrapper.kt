package com.mobile.pablo.uicomponents.views.wrapper

import com.mobile.pablo.core.util.EMPTY_STRING

data class VideoItemWrapper(
    val title: String,
    val channelName: String,
    val description: String,
    val imageUrl: String,
    val duration: String? = null,
    val videoId: String
) {

    companion object {

        fun empty() =
            VideoItemWrapper(
                title = EMPTY_STRING,
                channelName = EMPTY_STRING,
                description = EMPTY_STRING,
                imageUrl = EMPTY_STRING,
                duration = EMPTY_STRING,
                videoId = EMPTY_STRING
            )
    }
}
