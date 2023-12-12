package com.mobile.pablo.home.ext

import androidx.navigation.NavController
import com.mobile.pablo.error.screen.destinations.ErrorScreenDestination
import com.mobile.pablo.uicomponents.ext.navigateTo
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

internal fun navigateToErrorScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController
) = navigateTo(
    destinationsNavigator = destinationsNavigator,
    navController = navController,
    direction = ErrorScreenDestination()
)
