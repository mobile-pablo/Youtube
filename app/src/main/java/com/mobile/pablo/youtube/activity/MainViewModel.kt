package com.mobile.pablo.youtube.activity

import androidx.lifecycle.ViewModel
import com.mobile.pablo.core.ext.launchAsync
import com.mobile.pablo.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAuthStatusUseCase: AuthUseCase.GetAuthStatus
) : ViewModel() {

    private var sharedPrefJob: Job? = null

    private var _authStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean>
        get() = _authStatus.asStateFlow()

    init {
        getAuthStatus()
    }

    private fun getAuthStatus() {
        sharedPrefJob?.cancel()
        sharedPrefJob = launchAsync { _authStatus.update { getAuthStatusUseCase() } }
    }
}
