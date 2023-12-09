package com.mobile.pablo.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
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
import com.mobile.pablo.uicomponents.theme.primaryColor
import com.mobile.pablo.uicomponents.theme.secondaryColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import com.mobile.pablo.uicomponents.theme.tertiarySelectedColor
import com.mobile.pablo.uicomponents.views.KeyboardView
import com.mobile.pablo.uicomponents.views.SearchBar
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.distinctUntilChanged
import androidx.compose.material.MaterialTheme as Theme

@OptIn(
    ExperimentalPermissionsApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalMaterialApi::class
)
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

        Scaffold(
            modifier = Modifier
                .padding(
                    vertical = Theme.spacing.spacing_8,
                    horizontal = Theme.spacing.spacing_16
                ),
            containerColor = Theme.colors.primaryColor
        ) { padding ->
            Row(
                modifier = Modifier
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = Theme.spacing.spacing_16,
                            vertical = Theme.spacing.spacing_16
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

                    FlowRow {
                        searchHistory.forEachIndexed { index, item ->
                            InputChip(
                                modifier = Modifier.padding(horizontal = Theme.spacing.spacing_4),
                                selected = searchHistory[index] == item,
                                onClick = {},
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Refresh,
                                        contentDescription = null
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    containerColor = Theme.colors.secondaryColor,
                                    selectedContainerColor = Theme.colors.secondaryColor,
                                    selectedLabelColor = Theme.colors.tertiarySelectedColor,
                                    labelColor = Theme.colors.tertiaryColor,
                                    iconColor = Theme.colors.tertiarySelectedColor
                                ),
                                label = { Text(text = item) }
                            )
                        }
                    }
                }

                KeyboardView(
                    modifier = Modifier
                        .fillMaxSize(0.3f)
                )
            }
        }
    }
}
