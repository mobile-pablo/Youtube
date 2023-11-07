package com.mobile.pablo.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.CircularProgressIndicator
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
    val popularLazyPagingItems: LazyPagingItems<PopularItem> =
        viewModel.popularState.collectAsLazyPagingItems()

    popularLazyPagingItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> HomeLoadingView()
            loadState.append is LoadState.Loading -> CircularProgressIndicator()

            loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                val errorState = loadState.refresh as LoadState.Error

                navigateToErrorScreen(
                    destinationsNavigator,
                    navController,
                    errorState.error
                )
            }

            else -> HomeDoneView(popularLazyPagingItems)
        }
    }
}

private const val GRID_COLUMNS = 2

@Composable
private fun HomeDoneView(popularItems: LazyPagingItems<PopularItem>) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(GRID_COLUMNS),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(popularItems.itemCount) { index ->
            val item = popularItems[index]
            HomeItemView(
                HomeItemWrapper(
                    title = item!!.snippet!!.title!!,
                    description = item.snippet!!.description!!,
                    imageUrl = item.snippet!!.thumbnails!!.high!!.url!!,
                    videoId = item.id!!
                )
            )
        }
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

private fun navigateToErrorScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController,
    error: Throwable
) {
    val errorScreenDestination = ErrorScreenDestination(error)
    val navGraphSpec: NavGraphSpec = navController.currentBackStackEntry!!.navGraph()
    destinationsNavigator.navigate(errorScreenDestination within navGraphSpec)
}