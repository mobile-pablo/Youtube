package com.mobile.pablo.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mobile.pablo.search.R
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Destination
fun SearchEntryScreen() {
    val recordAudioPermission = rememberPermissionState(
        android.Manifest.permission.RECORD_AUDIO
    )
    recordAudioPermission.apply {
        Column {
            Text("Search entry screen")
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    if (!status.isGranted) recordAudioPermission.launchPermissionRequest()
                }
            ) {
                Icon(painterResource(R.drawable.ic_mic_24), null)
            }
        }
    }
}
