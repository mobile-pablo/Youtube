package com.mobile.pablo.search.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.mobile.pablo.domain.model.search.SearchItem
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
internal fun SearchDoneView(
    searchItems: LazyPagingItems<SearchItem>,
    destinationsNavigator: DestinationsNavigator,
    navController: NavController
) {}
