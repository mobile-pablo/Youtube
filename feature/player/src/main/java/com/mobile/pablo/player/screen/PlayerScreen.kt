package com.mobile.pablo.player.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.mobile.pablo.player.views.youtubePlayerView
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun PlayerScreen(videoId: String) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.Black),
        verticalArrangement = Arrangement.Center
    ) {
        AndroidView(factory = {
            youtubePlayerView(videoId, it)
        })
    }
}
