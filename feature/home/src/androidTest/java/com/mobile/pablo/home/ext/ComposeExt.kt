package com.mobile.pablo.home.ext

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertTextEquals

fun SemanticsNodeInteraction.exists() = assertExists()

fun SemanticsNodeInteraction.textEquals(text: String) = assertTextEquals(text)
