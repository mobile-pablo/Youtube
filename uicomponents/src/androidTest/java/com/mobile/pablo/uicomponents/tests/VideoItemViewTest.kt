package com.mobile.pablo.uicomponents.tests

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.uicomponents.views.common.VideoItemView
import com.mobile.pablo.uicomponents.views.wrapper.VideoItemWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class VideoItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var wrapper: VideoItemWrapper

    @Before
    fun setup() {
        navController = TestNavHostController(context)
    }

    @Test
    fun assertVideoItemViewFilledIsDisplayed() {
        composeTestRule.apply {
            setContent {
                wrapper =
                    VideoItemWrapper(
                        title = "title",
                        channelName = "channelName",
                        description = "description",
                        imageUrl = "imageUrl",
                        duration = "duration",
                        videoId = "videoId"
                    )
                VideoItemView(wrapper, navController = navController, navigateToPlayer = { _, _, _ -> })
            }
            waitForIdle()
            onNodeWithText(wrapper.title).assertExists()
            onNodeWithText(wrapper.channelName).assertExists()
        }
    }

    @Test
    fun assertVideoItemViewEmptyDoesntExist() {
        wrapper = VideoItemWrapper.empty()
        composeTestRule.apply {
            setContent {
                VideoItemView(wrapper, navController = navController, navigateToPlayer = { _, _, _ -> })
            }

            onNodeWithText(wrapper.title).assertTextEquals(EMPTY_STRING)
            onNodeWithText(wrapper.channelName).assertTextEquals(EMPTY_STRING)
        }
    }
}
