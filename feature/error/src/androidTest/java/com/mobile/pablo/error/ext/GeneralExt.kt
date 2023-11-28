package com.mobile.pablo.error.ext

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.platform.app.InstrumentationRegistry

fun stringRes(@StringRes id: Int): String {
    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    return context.resources.getString(id)
}

fun SemanticsNodeInteraction.isDisplayed(): SemanticsNodeInteraction = assertIsDisplayed()
