package com.mobile.pablo.home.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mobile.pablo.domain.model.popular.Popular
import com.mobile.pablo.domain.model.popular.PopularItem
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
                    item { Text(text = "Loading") }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = popularLazyPagingItems.loadState.refresh as LoadState.Error
                    item {
                        Text(text = "error : ${error.error.localizedMessage}")
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.append is LoadState.Error -> {
                    val error = popularLazyPagingItems.loadState.append as LoadState.Error
                    item {
                        Text(text = "error : ${error.error.localizedMessage}")
                    }
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