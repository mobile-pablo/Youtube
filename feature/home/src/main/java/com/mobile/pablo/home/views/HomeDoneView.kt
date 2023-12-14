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
import com.mobile.pablo.player.screen.destinations.PlayerScreenDestination
import com.mobile.pablo.uicomponents.ext.navigateTo
import com.mobile.pablo.uicomponents.views.common.VideoItemView
import com.mobile.pablo.uicomponents.views.wrapper.VideoItemWrapper
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
internal fun HomeDoneView(
    popularItems: LazyPagingItems<PopularItem>,
    destinationsNavigator: DestinationsNavigator,
    navController: NavController
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(GRID_COLUMNS),
        modifier = Modifier.fillMaxSize()
    ) {
        items(popularItems.itemCount) { index ->
            val item = popularItems[index]
            item?.apply {
                snippet?.apply {
                    VideoItemView(
                        wrapper = VideoItemWrapper(
                            title = title ?: EMPTY_STRING,
                            channelName = channelTitle ?: EMPTY_STRING,
                            description = description ?: EMPTY_STRING,
                            imageUrl = thumbnails!!.medium!!.url!!,
                            videoId = id!!,
                            duration = contentDetails!!.duration ?: EMPTY_STRING
                        ),
                        destinationsNavigator = destinationsNavigator,
                        navController = navController,
                        navigateToPlayer = { destinationsNavigator, navController, videoId ->
                            onVideoItemClick(
                                destinationsNavigator,
                                navController,
                                videoId
                            )
                        }
                    )
                }
            }
        }
    }
}

private fun onVideoItemClick(
    destinationsNavigator: DestinationsNavigator? = null,
    navController: NavController,
    videoId: String
) {
    destinationsNavigator?.let {
        navigateTo(
            destinationsNavigator = it,
            navController = navController,
            direction = PlayerScreenDestination(
                videoId = videoId
            )
        )
    }
}

private const val GRID_COLUMNS = 3
