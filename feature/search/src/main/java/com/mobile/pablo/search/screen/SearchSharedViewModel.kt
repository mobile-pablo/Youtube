package com.mobile.pablo.search.screen

import androidx.lifecycle.ViewModel
import com.mobile.pablo.domain.usecase.VideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.distinctUntilChanged

@HiltViewModel
class SearchSharedViewModel
    @Inject
    constructor(
        getSearchHistories: VideosUseCase.GetSearchHistories
    ) : ViewModel() {
        val searchHistory = getSearchHistories()
            .distinctUntilChanged()
    }
