package com.mobile.pablo.core.ext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
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

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}
