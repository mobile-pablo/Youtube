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
import com.mobile.pablo.home.views.HomeItemView
import com.mobile.pablo.home.wrapper.HomeItemWrapper
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
        is Loading -> HomeLoadingView()
        is Done -> HomeDoneView(homeState.value as Done)
        is Error -> HomeErrorView(homeState.value as Error)
    }
}

@Composable
private fun HomeLoadingView() {
    Text(text = "Loading")
}

@Composable
private fun HomeDoneView(state: Done) {
    LazyColumn {
        items(state.data.items!!) { item ->
            HomeItemView(
                HomeItemWrapper(
                    title = item!!.snippet!!.title!!,
                    description = item.snippet!!.description!!,
                    imageUrl = item.snippet!!.thumbnails!!.medium!!.url!!,
                    videoId = item.id!!
                )
            )
        }
    }
}

@Composable
private fun HomeErrorView(state: Error) {
    Text(text = state.error.toString())
}