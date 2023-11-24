package com.mobile.pablo.home.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import com.mobile.pablo.uicomponents.theme.progressBackgroundColor
import com.mobile.pablo.uicomponents.theme.progressColor
import com.mobile.pablo.uicomponents.theme.spacing

@Composable
internal fun HomeLoadingView() {
    LinearProgressIndicator(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.spacing_48)
            .fillMaxWidth()
            .height(MaterialTheme.spacing.spacing_16),
        color = MaterialTheme.colors.progressColor,
        backgroundColor = MaterialTheme.colors.progressBackgroundColor,
        strokeCap = StrokeCap.Square
    )
}