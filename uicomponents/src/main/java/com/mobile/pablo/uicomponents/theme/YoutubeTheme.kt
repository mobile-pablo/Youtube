package com.mobile.pablo.uicomponents.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material.MaterialTheme as Theme

typealias contentCompose = @Composable () -> Unit

@Composable
fun YoutubeTheme(
    content: contentCompose
) {

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalFontSize provides FontSize()
    ) {
        Theme(
            shapes = Shapes,
            content = content
        )
    }
}