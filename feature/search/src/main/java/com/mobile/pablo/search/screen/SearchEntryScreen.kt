package com.mobile.pablo.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.mobile.pablo.core.ext.findActivity
import com.mobile.pablo.search.R
import com.mobile.pablo.search.data.VoiceToTextParser
import com.mobile.pablo.search.data.VoiceToTextParserState
import com.mobile.pablo.search.screen.destinations.SearchResultScreenDestination
import com.mobile.pablo.search.view.RecordFab
import com.mobile.pablo.search.view.SearchHistoryChips
import com.mobile.pablo.uicomponents.ext.clear
import com.mobile.pablo.uicomponents.ext.navigateTo
import com.mobile.pablo.uicomponents.theme.primaryColor
import com.mobile.pablo.uicomponents.theme.secondaryColor
import com.mobile.pablo.uicomponents.theme.secondarySelectedColor
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.uicomponents.theme.tertiaryColor
import com.mobile.pablo.uicomponents.theme.tertiarySelectedColor
import com.mobile.pablo.uicomponents.views.common.SearchBar
import com.mobile.pablo.uicomponents.views.keyboard.view.KeyboardView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.distinctUntilChanged
import androidx.compose.material.MaterialTheme as Theme

@OptIn(
    ExperimentalPermissionsApi::class
)
@Composable
@Destination
fun SearchEntryScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController = rememberNavController(),
    viewModel: SearchSharedViewModel = hiltViewModel()
) {
    val recordAudioPermission = rememberPermissionState(
        android.Manifest.permission.RECORD_AUDIO
    )

    val query = remember { mutableStateOf(TextFieldValue()) }
    val searchHistory by viewModel.searchHistory
        .collectAsStateWithLifecycle(initialValue = emptyList())

    val context = LocalContext.current
    val activity = context.findActivity()
    val application = activity.application

    val voiceToText by lazy {
        VoiceToTextParser(
            application,
            updateStatus = {
                searchFromQuery(
                    it,
                    viewModel,
                    destinationsNavigator,
                    navController
                )
            }
        )
    }

    recordAudioPermission.apply {
        val state by voiceToText.state
            .distinctUntilChanged { old, new -> old.spokenText == new.spokenText }
            .collectAsState(VoiceToTextParserState())

        Scaffold(
            containerColor = Theme.colors.primaryColor
        ) { padding ->
            Row(modifier = Modifier.padding(padding)) {
                Column(
                    modifier = Modifier
                        .padding(vertical = Theme.spacing.spacing_12)
                        .fillMaxSize(LEFT_BOX_WEIGHT)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            vertical = Theme.spacing.spacing_8,
                            horizontal = Theme.spacing.spacing_16
                        )
                    ) {
                        SearchBar(
                            textFieldState = query,
                            modifier = Modifier
                                .weight(5f)
                                .padding(end = Theme.spacing.spacing_8),
                            hint = stringResource(id = R.string.search_videos)
                        )

                        RecordFab(
                            modifier = Modifier.wrapContentHeight(),
                            state = state,
                            recordAudioPermission = recordAudioPermission,
                            voiceToText = voiceToText
                        )
                    }

                    SearchHistoryChips(
                        searchHistory = searchHistory,
                        onClick = {
                            searchFromQuery(
                                it,
                                viewModel,
                                destinationsNavigator,
                                navController
                            )
                        }
                    )
                }

                KeyboardView(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = Theme.colors.primaryColor,
                    buttonBackgroundColor = Theme.colors.secondaryColor,
                    buttonSelectedBackgroundColor = Theme.colors.secondarySelectedColor,
                    buttonTextColor = Theme.colors.tertiaryColor,
                    buttonSelectedTextColor = Theme.colors.tertiarySelectedColor,
                    textFieldState = query,
                    onAction = {
                        searchFromQuery(
                            query,
                            viewModel,
                            destinationsNavigator,
                            navController
                        )
                    }
                )
            }
        }
    }
}

private fun navigateToResultScreen(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController,
    query: String
) = navigateTo(
    destinationsNavigator = destinationsNavigator,
    navController = navController,
    direction = SearchResultScreenDestination(query)
)

private fun searchFromQuery(
    query: String,
    viewModel: SearchSharedViewModel,
    destinationsNavigator: DestinationsNavigator,
    navController: NavController
) {
    viewModel.upsertSearchHistoryItem(query)
    navigateToResultScreen(
        destinationsNavigator = destinationsNavigator,
        navController = navController,
        query = query
    )
}

private fun searchFromQuery(
    queryState: MutableState<TextFieldValue>,
    viewModel: SearchSharedViewModel,
    destinationsNavigator: DestinationsNavigator,
    navController: NavController
) {
    val query = queryState.value.text
    searchFromQuery(query, viewModel, destinationsNavigator, navController)
    queryState.clear()
}

private const val LEFT_BOX_WEIGHT = 0.7f
