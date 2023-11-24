package com.mobile.pablo.home.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.mobile.pablo.domain.model.popular.PopularItem
import com.mobile.pablo.home.wrapper.HomeItemWrapper
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private const val GRID_COLUMNS = 2

@Composable
internal fun HomeDoneView(
    popularItems: LazyPagingItems<PopularItem>,
    destinationsNavigator: DestinationsNavigator,
    navController: NavController
) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(GRID_COLUMNS),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(popularItems.itemCount) { index ->
            val item = popularItems[index]
            HomeItemView(
                wrapper = HomeItemWrapper(
                    title = item!!.snippet!!.title!!,
                    description = item.snippet!!.description!!,
                    imageUrl = item.snippet!!.thumbnails!!.high!!.url!!,
                    videoId = item.id!!
                ),
                destinationsNavigator = destinationsNavigator,
                navControler = navController
            )
        }
    }
}