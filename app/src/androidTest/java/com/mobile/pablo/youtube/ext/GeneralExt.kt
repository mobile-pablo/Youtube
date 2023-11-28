package com.mobile.pablo.youtube.ext

import android.content.Context
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry

fun stringRes(
    @StringRes id: Int
): String {
    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    return context.resources.getString(id)
}
