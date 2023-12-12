package com.mobile.pablo.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
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
import com.mobile.pablo.domain.model.search.SearchItem
import com.mobile.pablo.search.ext.navigateToErrorScreen
import com.mobile.pablo.search.view.SearchDoneView
import com.mobile.pablo.uicomponents.theme.primaryColor
import com.mobile.pablo.uicomponents.theme.secondaryColor
import com.mobile.pablo.uicomponents.views.keyboard.view.LoadingView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun SearchResultScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController = rememberNavController(),
    viewModel: SearchSharedViewModel = hiltViewModel(),
    query: String
) {
    val searchLazyPagingItems: LazyPagingItems<SearchItem> =
        viewModel.getSearch(query).collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryColor),
        contentAlignment = Alignment.Center
    ) {
        searchLazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> LoadingView()
                loadState.append is LoadState.Loading ->
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.secondaryColor
                    )

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    refresh()
                    navigateToErrorScreen(
                        destinationsNavigator,
                        navController
                    )
                }
            }
            SearchDoneView(
                this,
                destinationsNavigator,
                navController
            )
        }
    }
}
