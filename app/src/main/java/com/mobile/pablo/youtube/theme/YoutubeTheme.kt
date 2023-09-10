package com.mobile.pablo.youtube.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.mobile.pablo.youtube.theme.FontSize
import com.mobile.pablo.youtube.theme.LocalFontSize
import com.mobile.pablo.youtube.theme.Typography
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
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
