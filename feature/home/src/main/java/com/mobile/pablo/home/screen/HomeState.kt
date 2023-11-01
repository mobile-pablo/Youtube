package com.mobile.pablo.home.screen

import com.mobile.pablo.domain.model.Popular

sealed class HomeState {
    data object Loading : HomeState()
    data class Done(val data: Popular) : HomeState()
    data class Error(val error: Exception) : HomeState()
}