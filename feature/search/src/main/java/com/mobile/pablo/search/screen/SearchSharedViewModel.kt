package com.mobile.pablo.search.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobile.pablo.core.ext.launchAsync
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.domain.model.search.SearchItem
import com.mobile.pablo.domain.usecase.SearchHistoryUseCase
import com.mobile.pablo.domain.usecase.VideosUseCase
import com.mobile.pablo.storage.database.const.SEARCH_HISTORY_LIMIT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.shareIn

@HiltViewModel
class SearchSharedViewModel @Inject constructor(
    getSearchHistoryUseCase: SearchHistoryUseCase.GetSearchHistory,
    val getSearchVideos: VideosUseCase.GetSearchVideos,
    val upsertSearchHistoryItemUseCase: SearchHistoryUseCase.UpsertSearchHistoryItem
) : ViewModel() {

    private var searchHistoryJob: Job? = null
    private var _query = MutableStateFlow(EMPTY_STRING)
    val query: StateFlow<String> get() = _query

    fun setQuery(q: String) {
        _query.value = q
    }

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

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchItemsState: Flow<PagingData<SearchItem>> =
        query.flatMapLatest { query ->
            getSearchVideos(query)
        }.shareIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(POPULAR_VIDEO_DEBOUNCE_MILLIS),
            replay = 1
        ).cachedIn(viewModelScope)

    companion object {

        private const val POPULAR_VIDEO_DEBOUNCE_MILLIS = 6000L
    }
}
