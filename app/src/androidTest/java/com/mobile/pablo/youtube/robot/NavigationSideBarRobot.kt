package com.mobile.pablo.youtube.robot

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.youtube.R
import com.mobile.pablo.youtube.ext.isDisplayed
import com.mobile.pablo.youtube.ext.stringRes

internal class NavigationSideBarRobot(
    private val composeTestRule: ComposeTestRule
) {
    private val sideBarContentColumn by lazy {
        val tag = stringRes(R.string.side_bar_item_column)
        composeTestRule.onNodeWithTag(tag)
    }

    fun isSideBarContentColumnIsDisplayed(expectedSize: Int) {
        sideBarContentColumn.isDisplayed()
        val childrenNodes = sideBarContentColumn.fetchSemanticsNode().children
        assertThat(childrenNodes.size).isEqualTo(expectedSize)
    }
}

internal fun navigationSideBarRobot(composeTestRule: ComposeTestRule, func: NavigationSideBarRobot.() -> Unit) =
    NavigationSideBarRobot(composeTestRule).apply(func)
