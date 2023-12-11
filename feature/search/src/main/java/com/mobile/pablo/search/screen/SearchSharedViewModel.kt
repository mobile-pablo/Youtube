package com.mobile.pablo.search.screen

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.ext.launchAsync
import com.mobile.pablo.domain.usecase.SearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest

@HiltViewModel
class SearchSharedViewModel @Inject constructor(
    getSearchHistoryUseCase: SearchHistoryUseCase.GetSearchHistory,
    val upsertSearchHistoryItemUseCase: SearchHistoryUseCase.UpsertSearchHistoryItem
) : ViewModel() {

    private var searchHistoryJob: Job? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchHistory = getSearchHistoryUseCase()
        .distinctUntilChanged()
        .mapLatest {
            it.takeLast(SEARCH_HISTORY_LIMIT)
        }

    fun upsertSearchHistoryItem(query: String) {
        if (query.isBlank()) return
        searchHistoryJob?.cancel()
        searchHistoryJob = launchAsync { upsertSearchHistoryItemUseCase(query) }
    }

    companion object {

        private const val SEARCH_HISTORY_LIMIT = 15
    }
}
