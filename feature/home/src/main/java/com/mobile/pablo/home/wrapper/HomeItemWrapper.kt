package com.mobile.pablo.home.wrapper

import com.mobile.pablo.core.util.EMPTY_STRING

internal data class HomeItemWrapper(
    val title: String,
    val channelName: String,
    val description: String,
    val imageUrl: String,
    val duration: String,
    val videoId: String
) {
    companion object {
        fun empty() =
            HomeItemWrapper(
                title = EMPTY_STRING,
                channelName = EMPTY_STRING,
                description = EMPTY_STRING,
                imageUrl = EMPTY_STRING,
                duration = EMPTY_STRING,
                videoId = EMPTY_STRING
            )
    }
}
