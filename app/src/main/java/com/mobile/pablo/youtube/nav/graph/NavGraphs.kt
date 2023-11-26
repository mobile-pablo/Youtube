package com.mobile.pablo.youtube.nav.graph

import androidx.annotation.Keep
import com.mobile.pablo.error.screen.destinations.ErrorScreenDestination
import com.mobile.pablo.home.screen.destinations.HomeScreenDestination
import com.mobile.pablo.player.screen.destinations.PlayerScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

@Keep
object NavGraphs {

    val main = object : NavGraphSpec {
        override val route = "main"

        override val startRoute = HomeScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            HomeScreenDestination,
            PlayerScreenDestination,
            ErrorScreenDestination
        ).routedIn(this)
            .associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = main
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(main)
    }
}