package com.mobile.pablo.search.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.mobile.pablo.search.R
import com.mobile.pablo.search.data.VoiceToTextParser
import com.mobile.pablo.search.data.VoiceToTextParserState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RecordFab(
    state: VoiceToTextParserState,
    recordAudioPermission: PermissionState,
    voiceToText: VoiceToTextParser
) {
    FloatingActionButton(
        shape = CircleShape,
        onClick = {
            if (!recordAudioPermission.status.isGranted) {
                recordAudioPermission.launchPermissionRequest()
            } else {
                if (!state.isSpeaking) {
                    voiceToText.startListening("en")
                } else {
                    voiceToText.stopListening()
                }
            }
        }
    ) {
        AnimatedContent(targetState = state.isSpeaking, label = "") { isSpeaking ->
            if (isSpeaking) {
                Icon(painterResource(R.drawable.ic_stop_24), null)
            } else {
                Icon(painterResource(R.drawable.ic_mic_24), null)
            }
        }
    }
}
