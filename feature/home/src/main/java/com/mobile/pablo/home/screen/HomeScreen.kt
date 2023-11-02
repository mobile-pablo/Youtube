package com.mobile.pablo.home.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.pablo.home.screen.HomeState.Done
import com.mobile.pablo.home.screen.HomeState.Error
import com.mobile.pablo.home.screen.HomeState.Loading
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun HomeScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.getPopularVideos(regionCode = "US")
    val homeState = viewModel.homeState.collectAsState()
    when (homeState.value) {
        is Loading -> Text(text = "Loading")

        is Done -> {
            val homeStateDone = homeState.value as Done
            LazyColumn {
                items(homeStateDone.data.items!!) { item ->
                    Text(text = item!!.snippet!!.channelTitle!!)
                }
            }
        }

        is Error -> {
            Text(text = (homeState.value as Error).error.toString())
        }
    }
}