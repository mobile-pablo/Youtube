package com.mobile.pablo.error.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.tv.material3.Text
import com.mobile.pablo.error.R
import com.mobile.pablo.uicomponents.theme.bodyTextColor
import com.mobile.pablo.uicomponents.theme.primaryColor
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.material.MaterialTheme as Theme

@Composable
@Destination
fun ErrorScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.primaryColor)
    ) {
        Text(
            text = stringResource(id = R.string.missing_connection_title),
            color = Theme.colors.bodyTextColor
        )
    }
}