package com.mobile.pablo.youtube.ext

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed

fun SemanticsNodeInteraction.isDisplayed(): SemanticsNodeInteraction = assertIsDisplayed()
fun SemanticsNodeInteraction.doesNotExist() = assertDoesNotExist()
fun SemanticsNodeInteraction.exists() = assertExists()