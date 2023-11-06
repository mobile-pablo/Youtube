package com.mobile.pablo.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mobile.pablo.domain.model.popular.Popular
import com.mobile.pablo.domain.model.popular.PopularItem
import com.mobile.pablo.error.screen.destinations.ErrorScreenDestination
import com.mobile.pablo.home.views.HomeItemView
import com.mobile.pablo.home.wrapper.HomeItemWrapper
import com.mobile.pablo.uicomponents.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.utils.navGraph
import androidx.compose.material.MaterialTheme as Theme

@Composable
@Destination
fun HomeScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val popularLazyPagingItems: LazyPagingItems<Popular> = viewModel.popularState.collectAsLazyPagingItems()

    popularLazyPagingItems.apply {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(itemCount) { index ->
                popularLazyPagingItems[index]!!.items?.forEach {
                    it?.let {
                        HomeDoneView(it)
                    }
                }
            }
        }
        when {
            loadState.refresh is LoadState.Loading -> HomeLoadingView()

            loadState.refresh is LoadState.Error -> {
                val errorState = loadState.refresh as LoadState.Error
                navigateToErrorScreen(destinationsNavigator, navController, errorState.error)
            }

            loadState.append is LoadState.Loading -> HomeLoadingView()

            loadState.append is LoadState.Error -> {
                val errorState = loadState.append as LoadState.Error
                navigateToErrorScreen(destinationsNavigator, navController, errorState.error)
            }
        }
    }
}

@Composable
private fun HomeDoneView(item: PopularItem) {
    HomeItemView(
        HomeItemWrapper(
            title = item.snippet!!.title!!,
            description = item.snippet!!.description!!,
            imageUrl = item.snippet!!.thumbnails!!.medium!!.url!!,
            videoId = item.id!!
        )
    )
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

private fun navigateToErrorScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController,
    error: Throwable
) {
    val errorScreenDestination = ErrorScreenDestination(error)
    val navGraphSpec: NavGraphSpec = navController.currentBackStackEntry!!.navGraph()
    destinationsNavigator.navigate(errorScreenDestination within navGraphSpec)
}