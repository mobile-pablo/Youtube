package com.mobile.pablo.home.screen

import com.mobile.pablo.domain.model.PopularVideos

sealed class HomeState {
    data object Loading : HomeState()
    data class Done(val data: PopularVideos) : HomeState()
    data class Error(val error: Exception) : HomeState()
}