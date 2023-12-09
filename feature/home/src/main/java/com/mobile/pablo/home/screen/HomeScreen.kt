package com.mobile.pablo.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mobile.pablo.domain.model.popular.PopularItem
import com.mobile.pablo.error.screen.destinations.ErrorScreenDestination
import com.mobile.pablo.home.views.HomeDoneView
import com.mobile.pablo.home.views.HomeLoadingView
import com.mobile.pablo.uicomponents.ext.navigateTo
import com.mobile.pablo.uicomponents.theme.primaryColor
import com.mobile.pablo.uicomponents.theme.secondaryColor
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
    val popularLazyPagingItems: LazyPagingItems<PopularItem> =
        viewModel.popularState.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.primaryColor),
        contentAlignment = Alignment.Center
    ) {
        popularLazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> HomeLoadingView()
                loadState.append is LoadState.Loading ->
                    CircularProgressIndicator(
                        color = Theme.colors.secondaryColor
                    )

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    popularLazyPagingItems.refresh()
                    navigateToErrorScreen(
                        destinationsNavigator,
                        navController
                    )
                }
            }
            HomeDoneView(
                this,
                destinationsNavigator,
                navController
            )
        }
    }
}

private fun navigateToErrorScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController
) {
    navigateTo(
        destinationsNavigator = destinationsNavigator,
        navController = navController,
        direction = ErrorScreenDestination()
    )
}
