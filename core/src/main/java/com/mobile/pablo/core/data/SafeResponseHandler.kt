package com.mobile.pablo.core.data

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.core.util.TEXT_PLAIN
import java.net.HttpURLConnection
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
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
            (exception.message ?: EMPTY_STRING).toResponseBody(TEXT_PLAIN.toMediaTypeOrNull())
        )
    }
}
