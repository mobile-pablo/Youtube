package com.mobile.pablo.youtube.const

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import com.mobile.pablo.home.screen.destinations.HomeScreenDestination
import com.mobile.pablo.youtube.nav.model.NavigationItem

internal val NAVIGATION_ITEMS = listOf(
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