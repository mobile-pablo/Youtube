package com.mobile.pablo.youtube.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.youtube.const.MOCK_NAVIGATION_ITEMS
import com.mobile.pablo.youtube.ext.doesNotExist
import com.mobile.pablo.youtube.ext.exists
import com.mobile.pablo.youtube.ext.isDisplayed
import com.mobile.pablo.youtube.nav.view.BADGE
import com.mobile.pablo.youtube.nav.view.NavigationIcon
import org.junit.Rule
import org.junit.Test

class NavigationIconTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val item = MOCK_NAVIGATION_ITEMS.first()
    private val itemWNews = MOCK_NAVIGATION_ITEMS.elementAt(3)
    private val itemWCount = MOCK_NAVIGATION_ITEMS.last()

    @Test
    fun navIcon_displaysItemWhileSelected() {
        composeTestRule.setContent { NavigationIcon(item = item, selected = true) }
        composeTestRule.onNodeWithContentDescription(item.title).isDisplayed()
    }

    @Test
    fun navIcon_displaysItemWhileNotSelected() {
        composeTestRule.setContent { NavigationIcon(item = item, selected = false) }
        composeTestRule.onNodeWithContentDescription(item.title).isDisplayed()
    }

    @Test
    fun navIcon_displaysBadgeWithCount() {
        composeTestRule.setContent { NavigationIcon(item = itemWCount, selected = false) }
        composeTestRule.onNodeWithContentDescription(BADGE).exists()
    }

    @Test
    fun navIcon_wontDisplayBadgeCountWhileCountIsNull() {
        composeTestRule.setContent { NavigationIcon(item = item, selected = false) }
        composeTestRule.onNodeWithContentDescription(item.title).isDisplayed()
        assertThat(item.badgeCount).isNull()
        composeTestRule.onNodeWithContentDescription(BADGE).doesNotExist()
    }

    @Test
    fun navIcon_displayIconWithNews() {
        composeTestRule.setContent {
            NavigationIcon(item = itemWNews, selected = false)
        }
        composeTestRule.onNodeWithContentDescription(itemWNews.title).exists()
        composeTestRule.onNodeWithContentDescription(BADGE).exists()
    }
}