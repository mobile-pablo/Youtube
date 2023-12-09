package com.mobile.pablo.search.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.ext.launchAsync
import com.mobile.pablo.domain.usecase.SearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged

@HiltViewModel
class SearchSharedViewModel @Inject constructor(
    getSearchHistoryUseCase: SearchHistoryUseCase.GetSearchHistory,
    val upsertSearchHistoryItemUseCase: SearchHistoryUseCase.UpsertSearchHistoryItem
) : ViewModel() {

    private var searchHistoryJob: Job? = null

    var searchQuery by mutableStateOf("")

    val searchHistory = getSearchHistoryUseCase()
        .distinctUntilChanged()

    fun upsertSearchHistoryItem(query: String) {
        searchHistoryJob?.cancel()
        searchHistoryJob = launchAsync { upsertSearchHistoryItemUseCase(query) }
    }
}
