package com.mobile.pablo.playlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun PlaylistEntryScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController = rememberNavController()
) {
}
