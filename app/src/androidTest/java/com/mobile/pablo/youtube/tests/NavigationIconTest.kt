package com.mobile.pablo.youtube.tests

import androidx.compose.ui.test.junit4.createComposeRule
import com.mobile.pablo.youtube.const.MOCK_NAVIGATION_ITEMS
import com.mobile.pablo.youtube.nav.view.NavigationIcon
import com.mobile.pablo.youtube.robot.navigationIconRobot
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
        navigationIconRobot(composeTestRule, item) {
            isTitleDisplayed()
        }
    }

    @Test
    fun navIcon_displaysItemWhileNotSelected() {
        composeTestRule.setContent { NavigationIcon(item = item, selected = false) }
        navigationIconRobot(composeTestRule, item) {
            isTitleDisplayed()
        }
    }

    @Test
    fun navIcon_displaysBadgeWithCount() {
        composeTestRule.setContent { NavigationIcon(item = itemWCount, selected = false) }
        navigationIconRobot(composeTestRule) {
            doesBadgeExist()
        }
    }

    @Test
    fun navIcon_wontDisplayBadgeCountWhileCountIsNull() {
        composeTestRule.setContent { NavigationIcon(item = item, selected = false) }
        navigationIconRobot(composeTestRule, item) {
            doesNotBadgeExist()
            assertBadgeCountIsNull()
            isTitleDisplayed()
        }
    }

    @Test
    fun navIcon_displayIconWithNews() {
        composeTestRule.setContent {
            NavigationIcon(item = itemWNews, selected = false)
        }
        navigationIconRobot(composeTestRule, itemWNews) {
            doesBadgeExist()
            isTitleDisplayed()
        }
    }
}