package com.mobile.pablo.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.mobile.pablo.home.R
import com.mobile.pablo.home.wrapper.HomeItemWrapper
import com.mobile.pablo.uicomponents.theme.spacing
import androidx.compose.material.MaterialTheme as Theme

private const val TEXT_WRAP = 3

@Composable
fun HomeItemView(
    wrapper: HomeItemWrapper,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Theme.spacing.spacing_6))
            .clickable(onClick = ::onHomeItemClick)
            .padding(
                vertical = Theme.spacing.spacing_12,
                horizontal = Theme.spacing.spacing_32
            )
    ) {
        Row {
            Column {
                AsyncImage(
                    model = wrapper.imageUrl,
                    placeholder = painterResource(id = R.drawable.ic_image_24),
                    error = painterResource(id = R.drawable.ic_wifi_tethering_error_24),
                    contentDescription = null,
                    modifier = Modifier.size(width = Theme.spacing.spacing_160, height = Theme.spacing.spacing_90)
                        .clip(
                            RoundedCornerShape(Theme.spacing.spacing_6)
                        )
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = Theme.spacing.spacing_20)
            ) {
                Text(
                    text = wrapper.title,
                    modifier = Modifier
                        .padding(bottom = Theme.spacing.spacing_20),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = wrapper.description,
                    maxLines = TEXT_WRAP
                )
            }
        }
    }
}

private fun onHomeItemClick() {
}