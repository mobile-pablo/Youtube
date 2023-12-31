package com.mobile.pablo.youtube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mobile.pablo.uicomponents.theme.YoutubeTheme
import com.mobile.pablo.uicomponents.theme.primaryColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.youtube.const.NAVIGATION_ITEMS
import com.mobile.pablo.youtube.nav.graph.NavGraphs
import com.mobile.pablo.youtube.nav.view.NavigationSideBar
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.MaterialTheme as Theme

@Keep
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContent {
            val engine = rememberNavHostEngine()
            val navController = engine.rememberNavController()

            YoutubeTheme {
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationSideBar(
                        modifier = Modifier.width(Theme.spacing.spacing_72),
                        items = NAVIGATION_ITEMS,
                        selectedItemIndex = selectedItemIndex,
                        navController = navController
                    ) { selectedItemIndex = it }

                    DestinationsNavHost(
                        engine = engine,
                        navController = navController,
                        navGraph = NavGraphs.root,
                        startRoute = NavGraphs.root.startRoute,
                        modifier = Modifier
                            .padding(start = Theme.spacing.spacing_72)
                            .fillMaxSize()
                            .background(Theme.colors.primaryColor)
                    )
                }
            }
        }
    }
}
