package com.mobile.pablo.error.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.error.R
import com.mobile.pablo.error.ext.isDisplayed
import com.mobile.pablo.error.ext.stringRes
import com.mobile.pablo.error.screen.ErrorScreen
import com.mobile.pablo.error.screen.SEARCH_BADGE_TAG
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ErrorScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        navController = TestNavHostController(context)
        composeTestRule.setContent {
            ErrorScreen()
        }
    }

    @Test
    fun errorScreenDisplaysCorrectly() {
        composeTestRule.apply {
            onNodeWithText(stringRes(R.string.missing_connection_title))
                .isDisplayed()
            onNodeWithText(stringRes(R.string.missing_connection_description))
                .isDisplayed()
            onNodeWithText(stringRes(R.string.missing_connection_button_text))
                .isDisplayed()
        }
    }

    @Test
    fun clickingSearchIconNavigatesBack() {
        composeTestRule.onNodeWithContentDescription(SEARCH_BADGE_TAG).performClick()
        assertThat(navController.previousBackStackEntry).isNull()
    }
}
