package com.mobile.pablo.home.screen

import com.mobile.pablo.core.data.NetworkException
import com.mobile.pablo.domain.model.popular.Popular

sealed class HomeState {
    data object Loading : HomeState()
    data class Done(val data: Popular) : HomeState()
    data class Error(val error: NetworkException) : HomeState()
}