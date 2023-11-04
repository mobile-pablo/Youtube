package com.mobile.pablo.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.pablo.home.screen.HomeState.Done
import com.mobile.pablo.home.screen.HomeState.Error
import com.mobile.pablo.home.screen.HomeState.Loading
import com.mobile.pablo.home.views.HomeItemView
import com.mobile.pablo.home.wrapper.HomeItemWrapper
import com.mobile.pablo.uicomponents.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.material.MaterialTheme as Theme

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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .padding(horizontal = Theme.spacing.spacing_48)
                .fillMaxWidth()
                .height(Theme.spacing.spacing_16),
            color = Color.Red,
            backgroundColor = Color.LightGray,
            strokeCap = StrokeCap.Square
        )
    }
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