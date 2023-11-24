package com.mobile.pablo.player.views

import android.content.Context
import com.mobile.pablo.player.player.controller.menu.YoutubePlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

fun youtubePlayerView(
    videoId: String,
    context: Context
): YouTubePlayerView {
    val youTubePlayerView = YouTubePlayerView(context)
    youTubePlayerView.enableAutomaticInitialization = false
    val listener = object : AbstractYouTubePlayerListener() {

        override fun onReady(youTubePlayer: YouTubePlayer) {
            super.onReady(youTubePlayer)
            val controller = YoutubePlayerUiController(youTubePlayerView, youTubePlayer)
            controller.showYouTubeButton(false)
            youTubePlayerView.setCustomPlayerUi(controller.rootView)
            youTubePlayer.loadVideo(videoId, 0f)
        }
    }

    val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()

    youTubePlayerView.initialize(listener, options)
    return youTubePlayerView
}