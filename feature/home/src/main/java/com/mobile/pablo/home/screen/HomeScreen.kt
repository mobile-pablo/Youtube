package com.mobile.pablo.home.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
            is HomeState.Loading -> Text(text = "Loading")

            is HomeState.Done -> {
                val homeStateDone = homeState.value as HomeState.Done
                LazyColumn {
                    items(homeStateDone.data.items!!) { item ->
                        Text(text = item!!.snippet!!.description!!)
                    }
                }
            }

            is HomeState.Error -> {
                Text(text = (homeState.value as HomeState.Error).error.toString())
            }
        }
}