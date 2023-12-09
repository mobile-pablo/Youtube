package com.mobile.pablo.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.mobile.pablo.home.wrapper.HomeItemWrapper
import com.mobile.pablo.player.screen.destinations.PlayerScreenDestination
import com.mobile.pablo.uicomponents.ext.navigateTo
import com.mobile.pablo.uicomponents.theme.MONTSERRAT_FONT_FAMILY
import com.mobile.pablo.uicomponents.theme.bodyTextColor
import com.mobile.pablo.uicomponents.theme.font
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.views.AsyncImageWithProgress
import com.mobile.pablo.uicomponents.views.DurationView
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.material.MaterialTheme as Theme

@Composable
internal fun HomeItemView(
    wrapper: HomeItemWrapper,
    modifier: Modifier = Modifier,
    destinationsNavigator: DestinationsNavigator? = null,
    navController: NavController
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Theme.spacing.spacing_240)
            .clickable {
                onHomeItemClick(
                    destinationsNavigator = destinationsNavigator,
                    navController = navController,
                    videoId = wrapper.videoId
                )
            }
            .padding(
                vertical = Theme.spacing.spacing_12,
                horizontal = Theme.spacing.spacing_32
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                AsyncImageWithProgress(wrapper.imageUrl)
                DurationView(wrapper.duration)
            }

            Text(
                fontWeight = FontWeight.Medium,
                fontSize = Theme.font.font_12,
                fontFamily = MONTSERRAT_FONT_FAMILY,
                text = wrapper.title,
                color = Theme.colors.bodyTextColor,
                overflow = TextOverflow.Clip,
                maxLines = HOME_ITEM_TITLE_MAX_LINES,
                modifier = Modifier.padding(top = Theme.spacing.spacing_10)
            )

            Text(
                fontWeight = FontWeight.Normal,
                fontSize = Theme.font.font_9,
                fontFamily = MONTSERRAT_FONT_FAMILY,
                text = wrapper.channelName,
                color = Theme.colors.bodyTextColor,
                overflow = TextOverflow.Clip,
                maxLines = HOME_ITEM_CHANNEL_NAME_MAX_LINES,
                modifier = Modifier.padding(top = Theme.spacing.spacing_4)
            )
        }
    }
}

private fun onHomeItemClick(
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

private const val HOME_ITEM_TITLE_MAX_LINES = 2
private const val HOME_ITEM_CHANNEL_NAME_MAX_LINES = 1
