package com.mobile.pablo.youtube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mobile.pablo.uicomponents.theme.YoutubeTheme
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.youtube.const.NAVIGATION_ITEMS
import com.mobile.pablo.youtube.nav.graph.NavGraphs
import com.mobile.pablo.youtube.nav.view.NavigationSideBar
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.MaterialTheme as Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContent {
            YoutubeTheme {
                val navController = rememberNavController()
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationSideBar(
                        modifier = Modifier.width(Theme.spacing.spacing_82),
                        items = NAVIGATION_ITEMS,
                        selectedItemIndex = selectedItemIndex
                    ) { selectedItemIndex = it }
                    DestinationsNavHost(
                        navController = navController,
                        navGraph = NavGraphs.root,
                        modifier = Modifier.padding(start = Theme.spacing.spacing_82)
                    )
                }
            }
        }
    }
}