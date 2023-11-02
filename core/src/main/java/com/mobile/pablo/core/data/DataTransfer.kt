package com.mobile.pablo.core.data

import com.mobile.pablo.core.util.SERVICE_UNAVAILABLE

class DataTransfer<T>(
    val data: T? = null,
    val error: NetworkException? = null
) {
    val isSuccessful: Boolean = data != null
    val isServiceUnavailable = error?.code == SERVICE_UNAVAILABLE

    suspend fun <R> map(transform: suspend (T) -> R): DataTransfer<R> {
        return DataTransfer(
            data = data?.let {
                transform.invoke(it)
            },
            error = error
        )
    }

    fun doIfSuccessful(action: (T) -> Unit) {
        if (isSuccessful) {
            action.invoke(data!!)
        }
    }
}