package com.mobile.pablo.core.ext

import com.mobile.pablo.core.data.DataTransfer
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T, R> Response<T>.map(transform: suspend (T) -> R): DataTransfer<R> {
    return if (this.isSuccessful) {
        DataTransfer(transform.invoke(body()!!))
    } else {
        DataTransfer(error = HttpException(this))
    }
}
