package com.mobile.pablo.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mobile.pablo.domain.model.popular.PopularItem
import com.mobile.pablo.home.ext.navigateToErrorScreen
import com.mobile.pablo.home.views.HomeDoneView
import com.mobile.pablo.home.views.HomeShimmerVideoItem
import com.mobile.pablo.uicomponents.theme.primaryColor
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
                loadState.refresh is LoadState.Loading -> {
                    Column(
                        content = {
                            Box(
                                modifier = Modifier.height(16.dp)
                            )
                            LazyVerticalStaggeredGrid(
                                columns = StaggeredGridCells.Fixed(3),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(9) {
                                    HomeShimmerVideoItem()
                                }
                            }
                        }
                    )
                }

                loadState.refresh is LoadState.NotLoading -> {
                    HomeDoneView(
                        this,
                        destinationsNavigator,
                        navController
                    )
                }

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    refresh()
                    navigateToErrorScreen(
                        destinationsNavigator,
                        navController
                    )
                }

                else -> {
                    HomeDoneView(
                        this,
                        destinationsNavigator,
                        navController
                    )
                }
            }
        }
    }
}
