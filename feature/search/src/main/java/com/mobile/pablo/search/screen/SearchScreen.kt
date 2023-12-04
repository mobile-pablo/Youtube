package com.mobile.pablo.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Destination
fun SearchScreen() {
    val recordAudioPermissionState = rememberPermissionState(
        android.Manifest.permission.RECORD_AUDIO
    )

    Column {
        Button(onClick = { recordAudioPermissionState.launchPermissionRequest() }) {
            Text(text = "Request permission")
        }

        if (recordAudioPermissionState.status.isGranted) {
            Text("Camera permission Granted")
        } else {
            Text("Camera permission Denied")
        }
    }
}
