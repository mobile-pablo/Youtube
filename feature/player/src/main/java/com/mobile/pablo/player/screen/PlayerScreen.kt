package com.mobile.pablo.player.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun PlayerScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController = rememberNavController(),
    videoId: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center
    ) {
        AndroidView(factory = {
            val youTubePlayerView = YouTubePlayerView(it)
            youTubePlayerView.enableAutomaticInitialization = false
            val listener = object : AbstractYouTubePlayerListener() {

                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    val defaultPlayerUiController = DefaultPlayerUiController(youTubePlayerView, youTubePlayer)
                    defaultPlayerUiController.showYouTubeButton(false)
                    youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }

            val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()

            youTubePlayerView.initialize(listener, options)
            youTubePlayerView
        })
    }
}