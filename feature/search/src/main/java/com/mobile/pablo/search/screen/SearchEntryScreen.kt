package com.mobile.pablo.search.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mobile.pablo.core.ext.findActivity
import com.mobile.pablo.search.R
import com.mobile.pablo.search.data.VoiceToTextParser
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Destination
fun SearchEntryScreen() {
    val recordAudioPermission = rememberPermissionState(
        android.Manifest.permission.RECORD_AUDIO
    )

    val context = LocalContext.current
    val activity = context.findActivity()
    val application = activity.application

    val voiceToText by lazy {
        VoiceToTextParser(application)
    }
    recordAudioPermission.apply {
        Column {
            val state by voiceToText.state.collectAsState()

            Scaffold(
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        shape = CircleShape,
                        onClick = {
                            if (!status.isGranted) {
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
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedContent(targetState = state.isSpeaking, label = "") { isSpeaking ->
                        if (isSpeaking) {
                            Text(text = "Speak...")
                        } else {
                            Text(text = state.spokenText.ifEmpty { "Click on record" })
                        }
                    }
                }
            }
        }
    }
}
