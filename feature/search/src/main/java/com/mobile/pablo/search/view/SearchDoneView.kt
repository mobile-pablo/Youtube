package com.mobile.pablo.search.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.domain.model.search.SearchItem
import com.mobile.pablo.player.screen.destinations.PlayerScreenDestination
import com.mobile.pablo.uicomponents.ext.navigateTo
import com.mobile.pablo.uicomponents.views.common.VideoItemView
import com.mobile.pablo.uicomponents.views.wrapper.VideoItemWrapper
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
internal fun SearchDoneView(
    searchItems: LazyPagingItems<SearchItem>,
    destinationsNavigator: DestinationsNavigator,
    navController: NavController
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(GRID_COLUMNS),
        modifier = Modifier.fillMaxSize()
    ) {
        items(searchItems.itemCount) { index ->
            val item = searchItems[index]
            item?.apply {
                snippet?.apply {
                    VideoItemView(
                        wrapper = VideoItemWrapper(
                            title = title ?: EMPTY_STRING,
                            channelName = channelTitle ?: EMPTY_STRING,
                            description = description ?: EMPTY_STRING,
                            imageUrl = thumbnails!!.medium!!.url!!,
                            videoId = id!!.videoId!!
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
