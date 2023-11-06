package com.mobile.pablo.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.utils.navGraph

@Composable
@Destination
fun HomeScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val popularLazyPagingItems: LazyPagingItems<Popular> = viewModel.popularState.collectAsLazyPagingItems()
    LazyColumn() {
        items(popularLazyPagingItems.itemCount) { index ->
            popularLazyPagingItems[index]!!.items?.forEach {
                it?.let {
                    HomeDoneView(it)
                }
            }
        }
        popularLazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { HomeLoadingView() }
                }

                loadState.refresh is LoadState.Error -> {
                    val errorState = popularLazyPagingItems.loadState.refresh as LoadState.Error
                    navigateToErrorScreen(destinationsNavigator, navController, errorState.error)
                }

                loadState.append is LoadState.Loading -> {
                    item { HomeLoadingView() }
                }

                loadState.append is LoadState.Error -> {
                    val errorState = popularLazyPagingItems.loadState.append as LoadState.Error
                    navigateToErrorScreen(destinationsNavigator, navController, errorState.error)
                }
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
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
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