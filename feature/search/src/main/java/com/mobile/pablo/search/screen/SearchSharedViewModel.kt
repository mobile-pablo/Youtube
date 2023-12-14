package com.mobile.pablo.search.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobile.pablo.core.ext.launchAsync
import com.mobile.pablo.domain.model.search.SearchItem
import com.mobile.pablo.domain.usecase.SearchHistoryUseCase
import com.mobile.pablo.domain.usecase.VideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest

@HiltViewModel
class SearchSharedViewModel @Inject constructor(
    getSearchHistoryUseCase: SearchHistoryUseCase.GetSearchHistory,
    val getSearchVideos: VideosUseCase.GetSearchVideos,
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

    @OptIn(FlowPreview::class)
    val getSearch: Flow<PagingData<SearchItem>> =
        getSearchVideos("dog")
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .debounce(POPULAR_VIDEO_DEBOUNCE_MILLIS)

    companion object {

        private const val POPULAR_VIDEO_DEBOUNCE_MILLIS = 1000L
        private const val SEARCH_HISTORY_LIMIT = 15
    }
}
