package com.mobile.pablo.search.screen

import androidx.lifecycle.ViewModel
import com.mobile.pablo.domain.usecase.SearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.distinctUntilChanged

@HiltViewModel
class SearchSharedViewModel
@Inject
constructor(
    getSearchHistoryUseCase: SearchHistoryUseCase.GetSearchHistory,
    val upsertSearchHistoryItemUseCase: SearchHistoryUseCase.UpsertSearchHistoryItem
) : ViewModel() {

    val searchHistory = getSearchHistoryUseCase()
        .distinctUntilChanged()

    suspend fun upsertSearchHistoryItem(query: String) = upsertSearchHistoryItemUseCase(query)
}
