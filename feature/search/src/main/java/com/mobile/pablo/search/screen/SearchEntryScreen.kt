package com.mobile.pablo.search.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.mobile.pablo.core.ext.findActivity
import com.mobile.pablo.search.data.VoiceToTextParser
import com.mobile.pablo.search.view.RecordFab
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Destination
fun SearchEntryScreen(searchSharedViewModel: SearchSharedViewModel = hiltViewModel()) {
    val recordAudioPermission = rememberPermissionState(
        android.Manifest.permission.RECORD_AUDIO
    )

    val searchHistory = searchSharedViewModel.searchHistory
        .collectAsStateWithLifecycle(initialValue = emptyList())

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
                    RecordFab(
                        state = state,
                        recordAudioPermission = recordAudioPermission,
                        voiceToText = voiceToText
                    )
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
                            searchSharedViewModel.upsertSearchHistoryItem(state.spokenText)
                            Text(text = state.spokenText.ifEmpty { "Click on record" })
                        }
                    }
                }
            }
        }
    }
}
