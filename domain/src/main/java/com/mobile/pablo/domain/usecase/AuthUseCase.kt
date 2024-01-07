package com.mobile.pablo.domain.usecase

import com.mobile.pablo.storage.sharedprefs.Setting
import com.mobile.pablo.storage.sharedprefs.SharedPreferencesManager
import javax.inject.Inject

sealed class AuthUseCase {

    class GetAuthStatus @Inject constructor(
        private val sharedPreferencesManager: SharedPreferencesManager
    ) : AuthUseCase() {

        suspend operator fun invoke(): Boolean =
            !sharedPreferencesManager.getString(Setting.OAUTH_TOKEN).isNullOrBlank()
    }

    class InsertAuthToken @Inject constructor(
        private val sharedPreferencesManager: SharedPreferencesManager
    ) : AuthUseCase() {

        suspend operator fun invoke(token: String) {
            sharedPreferencesManager.setString(Setting.OAUTH_TOKEN, token)
        }
    }
}
