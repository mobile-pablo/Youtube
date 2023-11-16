package com.mobile.pablo.youtube.ext

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.performClick

fun SemanticsNodeInteraction.isNotDisplayed(): SemanticsNodeInteraction = assertIsNotDisplayed()
fun SemanticsNodeInteraction.isDisplayed(): SemanticsNodeInteraction = assertIsDisplayed()
fun SemanticsNodeInteraction.doesNotExist() = assertDoesNotExist()
fun SemanticsNodeInteraction.exists() = assertExists()
fun SemanticsNodeInteraction.click() = performClick()