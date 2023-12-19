package com.mobile.pablo.core.ext

import androidx.navigation.NavController

fun NavController.isRouteOnBackStack(route: String): Boolean {
    return runCatching { getBackStackEntry(route) }.isSuccess
}
