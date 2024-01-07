package com.mobile.pablo.youtube.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mobile.pablo.auth.destinations.AuthScreenDestination
import com.mobile.pablo.uicomponents.theme.YoutubeTheme
import com.mobile.pablo.uicomponents.theme.primaryColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.youtube.activity.MainViewModel
import com.mobile.pablo.youtube.const.NAVIGATION_ITEMS
import com.mobile.pablo.youtube.nav.ext.navigateTo
import com.mobile.pablo.youtube.nav.graph.NavGraphs
import com.mobile.pablo.youtube.nav.view.NavigationSideBar
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel<MainViewModel>()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val startRoute = if (!isLoggedIn)
        AuthScreenDestination routedIn NavGraphs.main
    else
        NavGraphs.main.startRoute

    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    YoutubeTheme {
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        Surface(modifier = Modifier.fillMaxSize()) {
            NavigationSideBar(
                modifier = Modifier.width(MaterialTheme.spacing.spacing_72),
                items = NAVIGATION_ITEMS,
                selectedItemIndex = selectedItemIndex,
                navController = navController
            ) { selectedItemIndex = it }

            DestinationsNavHost(
                engine = engine,
                navController = navController,
                navGraph = NavGraphs.main,
                startRoute = startRoute,
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.spacing_72)
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primaryColor)
            )
            showAuthWhenLoggedOut(isLoggedIn, navController)
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
private fun showAuthWhenLoggedOut(
    isLoggedIn: Boolean,
    navController: NavHostController
) {
    val currentDestination by navController.currentDestinationAsState()

    if (!isLoggedIn && currentDestination != AuthScreenDestination) {
        navController.navigateTo(AuthScreenDestination, NavGraphs.main)
    }

    // TODO : Hide a navigationRail when isNot logged In, Show otherwise
}
