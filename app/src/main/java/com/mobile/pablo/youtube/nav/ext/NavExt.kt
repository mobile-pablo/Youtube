package com.mobile.pablo.youtube.nav.ext

import androidx.navigation.NavController
import com.mobile.pablo.core.ext.isRouteOnBackStack
import com.mobile.pablo.youtube.nav.graph.NavGraphs
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

fun NavController.navigateTo(
    destination: DirectionDestinationSpec,
    navGraphSpec: NavGraphSpec
) {
    this.apply {
        val isCurrentDestOnBackStack = isRouteOnBackStack(destination.route)

        if (isCurrentDestOnBackStack) {
            popBackStack(destination.route, false)
            return
        }
        navigate(destination within navGraphSpec) {
            popUpTo(NavGraphs.root) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
