package com.mobile.pablo.error.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.tv.material3.Text
import com.mobile.pablo.error.R
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun ErrorScreen() {
    Text(text = stringResource(id = R.string.missing_connection_title))
}