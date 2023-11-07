package com.mobile.pablo.youtube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.mobile.pablo.home.screen.destinations.HomeScreenDestination

import com.mobile.pablo.uicomponents.theme.YoutubeTheme
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.youtube.nav.graph.NavGraphs
import com.mobile.pablo.youtube.nav.model.NavigationItem
import com.mobile.pablo.youtube.nav.view.NavigationSideBar
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            destination = HomeScreenDestination,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItem(
            title = "Playlist",
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List,
            hasNews = false,
            destination = HomeScreenDestination,
            badgeCount = 45
        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            destination = HomeScreenDestination,
            hasNews = true
        )
    )

    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubeTheme {
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        NavigationSideBar(
                            modifier = Modifier.width(MaterialTheme.spacing.spacing_82),
                            items = items,
                            selectedItemIndex = selectedItemIndex
                        ) {
                            selectedItemIndex = it
                        }
                        DestinationsNavHost(
                            navController = navController,
                            navGraph = NavGraphs.root,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}