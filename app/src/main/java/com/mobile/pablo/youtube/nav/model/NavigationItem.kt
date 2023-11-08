package com.mobile.pablo.youtube.nav.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DestinationSpec

data class NavigationItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val destination: DestinationSpec<*>,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null
)