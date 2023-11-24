package com.mobile.pablo.uicomponents.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mobile.pablo.uicomponents.R
import com.mobile.pablo.uicomponents.theme.progressColor
import com.mobile.pablo.uicomponents.theme.spacing

@Composable
fun AsyncImageWithProgress(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .clip(
                RoundedCornerShape(MaterialTheme.spacing.spacing_6)
            )
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading, AsyncImagePainter.State.Empty -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.progressColor
                )
            }

            is AsyncImagePainter.State.Error -> painterResource(id = R.drawable.ic_wifi_tethering_error_24)
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}