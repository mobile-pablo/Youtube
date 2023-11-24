package com.mobile.pablo.uicomponents.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.mobile.pablo.uicomponents.ext.formatDuration
import com.mobile.pablo.uicomponents.theme.backgroundAlphaColor
import com.mobile.pablo.uicomponents.theme.font
import com.mobile.pablo.uicomponents.theme.spacing
import androidx.compose.material.MaterialTheme as Theme

@Composable
fun DurationView(
    duration: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(
                bottom = Theme.spacing.spacing_8,
                end = Theme.spacing.spacing_8
            )
            .background(Theme.colors.backgroundAlphaColor)
            .padding(
                Theme.spacing.spacing_4
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = formatDuration(duration),
            fontSize = Theme.font.font_9,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}