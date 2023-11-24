package com.mobile.pablo.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.tv.material3.Text
import coil.compose.AsyncImagePainter.State
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mobile.pablo.home.R
import com.mobile.pablo.home.wrapper.HomeItemWrapper
import com.mobile.pablo.player.screen.destinations.PlayerScreenDestination
import com.mobile.pablo.uicomponents.ext.navigateTo
import com.mobile.pablo.uicomponents.theme.bodyTextColor
import com.mobile.pablo.uicomponents.theme.font
import com.mobile.pablo.uicomponents.theme.progressColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.material.MaterialTheme as Theme

@Composable
internal fun HomeItemView(
    wrapper: HomeItemWrapper,
    modifier: Modifier = Modifier,
    destinationsNavigator: DestinationsNavigator,
    navController: NavController
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
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
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                model = wrapper.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(Theme.spacing.spacing_6)
                    )
            ) {
                when (painter.state) {
                    is State.Loading, State.Empty -> CircularProgressIndicator(
                        color = Theme.colors.progressColor
                    )

                    is State.Error -> painterResource(id = R.drawable.ic_wifi_tethering_error_24)
                    is State.Success -> SubcomposeAsyncImageContent()
                }
            }
            Text(
                fontWeight = FontWeight.SemiBold,
                fontSize = Theme.font.font_12,
                text = wrapper.title,
                color = Theme.colors.bodyTextColor,
                overflow = TextOverflow.Clip,
                maxLines = HOME_ITEM_TITLE_MAX_LINES,
                modifier = Modifier
                    .padding(top = Theme.spacing.spacing_10)
            )

            Text(
                fontWeight = FontWeight.Normal,
                fontSize = Theme.font.font_9,
                text = wrapper.channelName,
                color = Theme.colors.bodyTextColor,
                overflow = TextOverflow.Clip,
                maxLines = HOME_ITEM_CHANNEL_NAME_MAX_LINES,
                modifier = Modifier
                    .padding(top = Theme.spacing.spacing_4)
            )
        }
    }
}

private fun onHomeItemClick(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController,
    videoId: String
) {
    navigateTo(
        destinationsNavigator = destinationsNavigator,
        navController = navController,
        direction = PlayerScreenDestination(
            videoId = videoId
        )
    )
}

private const val HOME_ITEM_TITLE_MAX_LINES = 2
private const val HOME_ITEM_CHANNEL_NAME_MAX_LINES = 1