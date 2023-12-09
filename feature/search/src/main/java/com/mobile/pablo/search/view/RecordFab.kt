package com.mobile.pablo.search.view

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.search.R
import com.mobile.pablo.search.data.VoiceToTextParser
import com.mobile.pablo.search.data.VoiceToTextParserState
import com.mobile.pablo.uicomponents.theme.secondaryColor
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import androidx.compose.material.MaterialTheme as Theme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RecordFab(
    modifier: Modifier = Modifier,
    state: VoiceToTextParserState,
    recordAudioPermission: PermissionState,
    voiceToText: VoiceToTextParser
) {
    FloatingActionButton(
        modifier = modifier,
        shape = CircleShape,
        containerColor = Theme.colors.tertiaryColor,
        onClick = {
            if (!recordAudioPermission.status.isGranted) {
                recordAudioPermission.launchPermissionRequest()
            } else {
                if (state.error != null) {
                    voiceToText.stopListening()
                    return@FloatingActionButton
                }

                if (!state.isSpeaking) {
                    voiceToText.startListening("en")
                } else {
                    voiceToText.stopListening()
                }
            }
        }
    ) {
        AnimatedContent(targetState = state, label = EMPTY_STRING) { state ->
            @DrawableRes
            val resId = when {
                state.isSpeaking -> R.drawable.ic_stop_24
                !state.isSpeaking -> R.drawable.ic_mic_24
                state.error != null -> R.drawable.ic_mic_24
                else -> null
            }

            resId?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = EMPTY_STRING,
                    tint = Theme.colors.secondaryColor
                )
            }
        }
    }
}
