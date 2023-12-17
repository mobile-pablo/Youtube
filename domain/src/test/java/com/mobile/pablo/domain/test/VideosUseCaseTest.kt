package com.mobile.pablo.domain.test

import androidx.paging.map
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.domain.mapper.common.ContentDetailsMapper
import com.mobile.pablo.domain.mapper.common.IdMapper
import com.mobile.pablo.domain.mapper.common.LocalizedMapper
import com.mobile.pablo.domain.mapper.common.StatisticsMapper
import com.mobile.pablo.domain.mapper.common.ThumbnailMapper
import com.mobile.pablo.domain.mapper.common.ThumbnailsMapper
import com.mobile.pablo.domain.mapper.popular.PopularItemMapper
import com.mobile.pablo.domain.mapper.popular.PopularSnippetMapper
import com.mobile.pablo.domain.mapper.search.SearchItemMapper
import com.mobile.pablo.domain.mapper.search.SearchSnippetMapper
import com.mobile.pablo.domain.mock.const.MOCK_DOG_SEARCH
import com.mobile.pablo.domain.mock.const.MOCK_POPULAR_ITEM
import com.mobile.pablo.domain.usecase.VideosUseCase
import com.mobile.pablo.networking.source.popular.PopularDataSource
import com.mobile.pablo.networking.source.search.SearchDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class VideosUseCaseTest {

    private lateinit var searchDataSource: SearchDataSource
    private lateinit var popularDataSource: PopularDataSource

    private val searchItemMapper = SearchItemMapper(
        IdMapper(),
        SearchSnippetMapper(
            ThumbnailsMapper(
                ThumbnailMapper()
            )
        )
    )

    private val popularItemMapper =
        PopularItemMapper(
            PopularSnippetMapper(
                ThumbnailsMapper(
                    ThumbnailMapper()
                ),
                LocalizedMapper()
            ),
            ContentDetailsMapper(),
            StatisticsMapper()
        )

    @Before
    fun setUp() {
        searchDataSource = mockk()
        popularDataSource = mockk()
    }

    @Test
    fun getSearchVideos_returnsSearchWhenSearchResponseIsSuccessful() =
        runTest {
            coEvery { searchDataSource.getSearchVideos("dog", EMPTY_STRING) } returns DataTransfer(MOCK_DOG_SEARCH)

            val getSearchVideos =
                VideosUseCase.GetSearchVideos(
                    searchDataSource = searchDataSource,
                    searchItemMapper = searchItemMapper
                )
            val result = getSearchVideos("dog")

            result.test {
                awaitItem().map {
                    assertThat(it).isEqualTo(MOCK_DOG_SEARCH.items!!.map(searchItemMapper::map))
                }
            }
        }

    @Test
    fun getSearchVideos_returnsLocalSearchWhenSearchResponseIsNotSuccessful() =
        runTest {
            coEvery { searchDataSource.getSearchVideos("dog", EMPTY_STRING) } returns DataTransfer(error = Exception())
            val getSearchVideos =
                VideosUseCase.GetSearchVideos(
                    searchDataSource = searchDataSource,
                    searchItemMapper = searchItemMapper
                )

            val result = getSearchVideos("dog")

            result.test {
                awaitItem().map {
                    assertThat(it).isEqualTo(MOCK_DOG_SEARCH.items!!.map(searchItemMapper::map))
                }
            }
        }

    @Test
    fun getSearchVideos_returnsErrorWhenSearchResponseIsNotSuccessful() =
        runTest {
            coEvery { searchDataSource.getSearchVideos("dog", EMPTY_STRING) } returns DataTransfer(error = Exception())

            val getSearchVideos =
                VideosUseCase.GetSearchVideos(
                    searchDataSource = searchDataSource,
                    searchItemMapper = searchItemMapper
                )
            val result = getSearchVideos("dog")

            result.test {
                awaitItem().map {
                    assertThat(it).isNull()
                }
            }
        }

    @Test
    fun getPopularVideos_pagingConfigReturnsGivenItem() =
        runTest {
            coEvery { popularDataSource.getPopularVideos("EN", "CAUSAS") } returns DataTransfer(MOCK_POPULAR_ITEM)

            val getPopularVideos =
                VideosUseCase.GetPopularVideos(
                    popularDataSource = popularDataSource,
                    popularItemMapper = popularItemMapper
                )

            val result = getPopularVideos()

            result.map { data ->
                data.map {
                    assertThat(it).isIn(MOCK_POPULAR_ITEM.items!!.map(popularItemMapper::map))
                }
            }
        }

    @Test
    fun getPopularVideos_pagingConfigReturnsEmptyData() =
        runTest {
            coEvery { popularDataSource.getPopularVideos("EN", "CAUSAS") } returns DataTransfer()

            val getPopularVideos =
                VideosUseCase.GetPopularVideos(
                    popularDataSource = popularDataSource,
                    popularItemMapper = popularItemMapper
                )

            val result = getPopularVideos()

            result.map { data ->
                data.map {
                    assertThat(it).isNotIn(MOCK_POPULAR_ITEM.items!!.map(popularItemMapper::map))
                }
            }
        }
}
