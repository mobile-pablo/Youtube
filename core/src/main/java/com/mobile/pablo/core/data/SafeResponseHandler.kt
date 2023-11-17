package com.mobile.pablo.core.data

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.core.util.TEXT_PLAIN
import java.net.HttpURLConnection
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import timber.log.Timber

suspend fun <T> callSafe(request: suspend () -> Response<T>): Response<T> {
    return try {
        request.invoke()
    } catch (exception: Exception) {
        Timber.e(exception)
        FirebaseCrashlytics.getInstance().recordException(exception)
        Response.error(
            HttpURLConnection.HTTP_UNSUPPORTED_TYPE,
            ResponseBody.create(MediaType.parse(TEXT_PLAIN), exception.message ?: EMPTY_STRING)
        )
    }
}