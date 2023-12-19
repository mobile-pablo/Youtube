package com.mobile.pablo.youtube.tests

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.mobile.pablo.uicomponents.theme.spacing
import com.mobile.pablo.youtube.MainActivity
import com.mobile.pablo.youtube.const.MOCK_NAVIGATION_ITEMS
import com.mobile.pablo.youtube.nav.view.NavigationSideBar
import com.mobile.pablo.youtube.robot.navigationSideBarRobot
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationSideBarTest {
    @JvmField
    @Rule
    val testRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        testRule.activity.setContent {
            var selectedItemIndex by rememberSaveable {
                mutableIntStateOf(0)
            }
            Surface(modifier = Modifier.fillMaxSize()) {
                NavigationSideBar(
                    modifier = Modifier.width(MaterialTheme.spacing.spacing_82),
                    items = MOCK_NAVIGATION_ITEMS,
                    selectedItemIndex = selectedItemIndex
                ) { selectedItemIndex = it }
            }
        }
    }

    @Test
    fun sideBar_ItemsAreDisplayed() {
        navigationSideBarRobot(testRule) {
            isSideBarContentColumnIsDisplayed(MOCK_NAVIGATION_ITEMS.size)
        }
    }
}
