package com.mobile.pablo.youtube.robot

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.youtube.ext.doesNotExist
import com.mobile.pablo.youtube.ext.exists
import com.mobile.pablo.youtube.nav.model.NavigationItem
import com.mobile.pablo.youtube.nav.view.BADGE

internal class NavigationIconRobot(
    private val composeTestRule: ComposeTestRule,
    private val item: NavigationItem? = null
) {
    private val badgeView by lazy { composeTestRule.onNodeWithContentDescription(BADGE) }
    private val titleView by lazy { composeTestRule.onNodeWithContentDescription(item!!.title) }
    private val badgeCount = item?.badgeCount

    fun doesBadgeExist() = badgeView.exists()

    fun doesNotBadgeExist() = badgeView.doesNotExist()

    fun isTitleDisplayed() = titleView.exists()

    fun assertBadgeCountIsNull() = assertThat(badgeCount).isNull()
}

internal fun navigationIconRobot(
    composeTestRule: ComposeTestRule,
    item: NavigationItem? = null,
    func: NavigationIconRobot.() -> Unit
) = NavigationIconRobot(composeTestRule, item).apply(func)
