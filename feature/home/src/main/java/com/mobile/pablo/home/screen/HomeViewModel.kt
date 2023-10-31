package com.mobile.pablo.home.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.ext.launchAsync
import com.mobile.pablo.domain.usecase.VideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularVideosUseCase: VideosUseCase.GetPopularVideos
) : ViewModel() {

    private var downloadJob: Job? = null

    private val _homeState: MutableState<HomeState> = mutableStateOf(value = HomeState.Loading)
    val homeState: State<HomeState> = _homeState

    fun getPopularVideos(regionCode: String) {
        downloadJob?.cancel()
        downloadJob = launchAsync {
            val response = getPopularVideosUseCase(regionCode = regionCode)
            _homeState.value = if (response.isSuccessful) {
                HomeState.Done(data = response.data!!)
            } else {
                HomeState.Error(error = response.error!!)
            }
        }
    }
}