package com.mobile.pablo.search.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.mobile.pablo.core.ext.findActivity
import com.mobile.pablo.search.data.VoiceToTextParser
import com.mobile.pablo.search.view.RecordFab
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.views.SearchBar
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.material.MaterialTheme as Theme

@OptIn(ExperimentalPermissionsApi::class, ExperimentalTvMaterial3Api::class)
@Composable
@Destination
fun SearchEntryScreen(searchSharedViewModel: SearchSharedViewModel = hiltViewModel()) {
    val recordAudioPermission = rememberPermissionState(
        android.Manifest.permission.RECORD_AUDIO
    )

    val searchHistory by searchSharedViewModel.searchHistory
        .collectAsStateWithLifecycle(initialValue = emptyList())

    val context = LocalContext.current
    val activity = context.findActivity()
    val application = activity.application

    val voiceToText by lazy {
        VoiceToTextParser(application)
    }
    val state by voiceToText.state.collectAsStateWithLifecycle()

    recordAudioPermission.apply {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = Theme.spacing.spacing_8,
                        horizontal = Theme.spacing.spacing_16
                    )
            ) {
                SearchBar(
                    modifier = Modifier
                        .weight(5f)
                        .padding(end = Theme.spacing.spacing_8),
                    onTextChange = {
                        searchSharedViewModel.searchQuery = it
                    },
                    hint = "Search",
                    onSearchClicked = {
                        searchSharedViewModel.upsertSearchHistoryItem(state.spokenText)
                    }
                )
                RecordFab(
                    modifier = Modifier
                        .wrapContentHeight(),
                    state = state,
                    recordAudioPermission = recordAudioPermission,
                    voiceToText = voiceToText
                )
            }

            AnimatedContent(targetState = state.isSpeaking, label = "") { isSpeaking ->
                if (isSpeaking) {
                    Text(text = "Speak...")
                } else {
                    Text(text = state.spokenText.ifEmpty { "Click on record" })
                    searchSharedViewModel.upsertSearchHistoryItem(state.spokenText)
                }
            }
        }
    }
}
