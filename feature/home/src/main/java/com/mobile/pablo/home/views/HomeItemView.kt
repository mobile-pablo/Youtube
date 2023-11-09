package com.mobile.pablo.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.tv.material3.Text
import coil.compose.AsyncImagePainter.State
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mobile.pablo.home.R
import com.mobile.pablo.home.wrapper.HomeItemWrapper
import com.mobile.pablo.uicomponents.theme.font
import com.mobile.pablo.uicomponents.theme.spacing
import androidx.compose.material.MaterialTheme as Theme

@Composable
internal fun HomeItemView(
    wrapper: HomeItemWrapper,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onHomeItemClick(wrapper.videoId)
            }
            .padding(
                vertical = Theme.spacing.spacing_12,
                horizontal = Theme.spacing.spacing_32
            )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth(),
            constraintSet = homeItemConstraints
        ) {
            SubcomposeAsyncImage(
                model = wrapper.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .layoutId(HOME_ITEM_IMAGE_ID)
                    .clip(
                        RoundedCornerShape(Theme.spacing.spacing_6)
                    )
            ) {
                when (painter.state) {
                    is State.Loading, State.Empty -> CircularProgressIndicator()
                    is State.Error -> painterResource(id = R.drawable.ic_wifi_tethering_error_24)
                    is State.Success -> SubcomposeAsyncImageContent()
                }
            }
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = Theme.font.font_23,
                text = wrapper.title,
                overflow = TextOverflow.Clip,
                maxLines = HOME_ITEM_TITLE_MAX_LINES,
                modifier = Modifier
                    .layoutId(HOME_ITEM_TITLE_ID)
                    .padding(top = Theme.spacing.spacing_20)
            )
        }
    }
}

private fun onHomeItemClick(videoUrl: String) {
    // TODO Implement opening Android Video Player
}

private val homeItemConstraints: ConstraintSet = ConstraintSet {
    val image = createRefFor(HOME_ITEM_IMAGE_ID)
    val title = createRefFor(HOME_ITEM_TITLE_ID)

    constrain(image) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(title) {
        top.linkTo(image.bottom)
        start.linkTo(image.start)
        end.linkTo(image.end)
    }
}
private const val HOME_ITEM_IMAGE_ID = "home_item_image_id"
private const val HOME_ITEM_TITLE_ID = "home_item_title_id"
private const val HOME_ITEM_TITLE_MAX_LINES = 1