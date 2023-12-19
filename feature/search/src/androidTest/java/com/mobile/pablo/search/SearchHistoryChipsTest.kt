package com.mobile.pablo.search

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.mobile.pablo.search.view.SearchHistoryChips
import org.junit.Rule
import org.junit.Test

class SearchHistoryChipsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun assertSearchHistoryChipsAreDisplayed() {
        val searchHistory = listOf("query1", "query2", "query3")

        composeTestRule.setContent {
            SearchHistoryChips(searchHistory, onClick = { })
        }

        searchHistory.forEach { query ->
            composeTestRule.onNodeWithText(query).assertExists()
        }
    }

    @Test
    fun assertNoChipsAreDisplayedWhenSearchHistoryIsEmpty() {
        val searchHistory = emptyList<String>()

        composeTestRule.setContent {
            SearchHistoryChips(searchHistory, onClick = {})
        }

        composeTestRule.onAllNodesWithText("query1").assertCountEquals(0)
    }
}
