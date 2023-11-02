package com.mobile.pablo.home.screen

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.ext.launchAsync
import com.mobile.pablo.domain.usecase.VideosUseCase
import com.mobile.pablo.home.screen.HomeState.Done
import com.mobile.pablo.home.screen.HomeState.Error
import com.mobile.pablo.home.screen.HomeState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularVideosUseCase: VideosUseCase.GetPopularVideos
) : ViewModel() {

    private var downloadJob: Job? = null

    private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow(value = Loading)
    val homeState: StateFlow<HomeState> = _homeState

    fun getPopularVideos(regionCode: String) {
        downloadJob?.cancel()
        downloadJob = launchAsync {
            val response = getPopularVideosUseCase(regionCode = regionCode)
            _homeState.value = if (response.isSuccessful) {
                Done(data = response.data!!)
            } else {
                Error(error = response.error!!)
            }
        }
    }
}