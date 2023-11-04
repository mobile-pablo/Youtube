package com.mobile.pablo.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobile.pablo.core.ext.launchAsync
import com.mobile.pablo.domain.model.popular.Popular
import com.mobile.pablo.domain.usecase.VideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularVideosUseCase: VideosUseCase.GetPopularVideos
) : ViewModel() {

    private var downloadJob: Job? = null

    private val _popularState: MutableStateFlow<PagingData<Popular>> =
        MutableStateFlow(value = PagingData.empty())
    val popularState: MutableStateFlow<PagingData<Popular>> get() = _popularState

    init {
        getPopularVideos()
    }

    private fun getPopularVideos() {
        downloadJob?.cancel()
        downloadJob = launchAsync {
            getPopularVideosUseCase().distinctUntilChanged().cachedIn(viewModelScope).collect {
                _popularState.value = it
            }
        }
    }
}