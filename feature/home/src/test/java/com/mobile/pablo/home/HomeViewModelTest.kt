package com.mobile.pablo.home

import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.domain.model.popular.PopularItem
import com.mobile.pablo.domain.usecase.VideosUseCase
import com.mobile.pablo.home.screen.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val getPopularVideosUseCase: VideosUseCase.GetPopularVideos = mockk()

    @Test
    fun `popularState emits expected data when use case is successful`() = runTest {
        val kind = "youtube#searchResult"
        val etag = "elhoJXuEkd1u5KN1JMxXeotpjic"
        val id = "SbTheNHE0VA"

        val expectedData = PagingData.from(
            listOf(PopularItem(kind = kind, etag = etag, id = id))
        )

        coEvery { getPopularVideosUseCase.invoke() } returns flowOf(expectedData)

        val viewModel = HomeViewModel(getPopularVideosUseCase)
        viewModel.popularState.test {
            awaitItem().map {
                assertThat(it.kind).isEqualTo("1")
                assertThat(it.etag).isEqualTo("Test Video")
                assertThat(it.id).isEqualTo("Test Description")
            }
        }
    }

    @Test
    fun `popularState emits empty data when use case returns empty data`() = runTest {
        val expectedData = PagingData.empty<PopularItem>()
        coEvery { getPopularVideosUseCase() } returns flowOf(expectedData)

        val viewModel = HomeViewModel(getPopularVideosUseCase)
        viewModel.popularState.test {
            awaitItem().map {
                assertThat(it).isNull()
            }
        }
    }
}