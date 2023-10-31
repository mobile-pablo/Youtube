package com.mobile.pablo.home.screen

import com.mobile.pablo.domain.model.Search

sealed class HomeState {
    data object Loading : HomeState()
    data class Done(val data: Search) : HomeState()
    data class Error(val error: Exception) : HomeState()
}