package com.mobile.pablo.home.tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.home.ext.exists
import com.mobile.pablo.home.ext.textEquals
import com.mobile.pablo.home.views.HomeItemView
import com.mobile.pablo.home.wrapper.HomeItemWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeItemViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var wrapper: HomeItemWrapper

    @Before
    fun setup() {
        navController = TestNavHostController(context)
    }

    @Test
    fun assertHomeItemViewFilledIsDisplayed() {
        composeTestRule.apply {
            setContent {
                wrapper =
                    HomeItemWrapper(
                        title = "title",
                        channelName = "channelName",
                        description = "description",
                        imageUrl = "imageUrl",
                        duration = "duration",
                        videoId = "videoId"
                    )
                HomeItemView(wrapper, navController = navController)
            }
            waitForIdle()
            onNodeWithText(wrapper.title).exists()
            onNodeWithText(wrapper.channelName).exists()
        }
    }

    @Test
    fun assertHomeItemViewEmptyDoesntExist() {
        wrapper = HomeItemWrapper.empty()
        composeTestRule.apply {
            setContent {
                HomeItemView(wrapper, navController = navController)
            }

            onNodeWithText(wrapper.title).textEquals(EMPTY_STRING)
            onNodeWithText(wrapper.channelName).textEquals(EMPTY_STRING)
        }
    }
}
