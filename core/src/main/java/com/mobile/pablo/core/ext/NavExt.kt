package com.mobile.pablo.core.ext

import androidx.navigation.NavController

fun NavController.isRouteOnBackStack(route: String): Boolean = runCatching { getBackStackEntry(route) }.isSuccess
