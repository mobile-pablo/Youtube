package com.mobile.pablo.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.mobile.pablo.core.ext.findActivity
import com.mobile.pablo.search.data.VoiceToTextParser
import com.mobile.pablo.search.data.VoiceToTextParserState
import com.mobile.pablo.search.view.RecordFab
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.views.SearchBar
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.distinctUntilChanged
import androidx.compose.material.MaterialTheme as Theme

@OptIn(ExperimentalPermissionsApi::class)
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
        VoiceToTextParser(
            application,
            updateStatus = {
                searchSharedViewModel.upsertSearchHistoryItem(it)
            }
        )
    }
    recordAudioPermission.apply {
        val state by voiceToText.state
            .distinctUntilChanged { old, new -> old.spokenText == new.spokenText }
            .collectAsState(VoiceToTextParserState())

        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Row(
                    modifier = Modifier.padding(
                        vertical = Theme.spacing.spacing_8,
                        horizontal = Theme.spacing.spacing_16
                    )
                ) {
                    SearchBar(
                        modifier = Modifier
                            .weight(5f)
                            .padding(end = Theme.spacing.spacing_8),
                        hint = "Search",
                        onSearchClicked = {
                            searchSharedViewModel.upsertSearchHistoryItem(it)
                        }
                    )

                    RecordFab(
                        modifier = Modifier.wrapContentHeight(),
                        state = state,
                        recordAudioPermission = recordAudioPermission,
                        voiceToText = voiceToText
                    )
                }

                LazyColumn {
                    items(searchHistory) { item ->
                        Text(text = item)
                    }
                }
            }
        }
    }
}
