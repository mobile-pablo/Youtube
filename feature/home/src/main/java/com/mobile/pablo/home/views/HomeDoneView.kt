package com.mobile.pablo.home.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.domain.model.popular.PopularItem
import com.mobile.pablo.home.wrapper.HomeItemWrapper
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private const val GRID_COLUMNS = 3

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
            item?.apply {
                snippet?.apply {
                    HomeItemView(
                        wrapper = HomeItemWrapper(
                            title = title ?: EMPTY_STRING,
                            channelName = channelTitle ?: EMPTY_STRING,
                            description = description ?: EMPTY_STRING,
                            imageUrl = thumbnails!!.high!!.url!!,
                            videoId = id!!,
                            videoLength = contentDetails!!.duration ?: EMPTY_STRING
                        ),
                        destinationsNavigator = destinationsNavigator,
                        navController = navController
                    )
                }
            }
        }
    }
}