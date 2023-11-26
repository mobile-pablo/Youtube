package com.mobile.pablo.domain.test

import androidx.paging.map
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.domain.mapper.common.ContentDetailsMapper
import com.mobile.pablo.domain.mapper.common.IdMapper
import com.mobile.pablo.domain.mapper.common.LocalizedMapper
import com.mobile.pablo.domain.mapper.common.PageInfoMapper
import com.mobile.pablo.domain.mapper.common.StatisticsMapper
import com.mobile.pablo.domain.mapper.common.ThumbnailMapper
import com.mobile.pablo.domain.mapper.common.ThumbnailsMapper
import com.mobile.pablo.domain.mapper.popular.PopularItemMapper
import com.mobile.pablo.domain.mapper.popular.PopularSnippetMapper
import com.mobile.pablo.domain.mapper.search.SearchItemMapper
import com.mobile.pablo.domain.mapper.search.SearchMapper
import com.mobile.pablo.domain.mapper.search.SearchSnippetMapper
import com.mobile.pablo.domain.mock.const.MOCK_DOG_SEARCH
import com.mobile.pablo.domain.mock.const.MOCK_POPULAR_ITEM
import com.mobile.pablo.domain.usecase.VideosUseCase
import com.mobile.pablo.networking.source.popular.PopularDataSource
import com.mobile.pablo.networking.source.search.SearchDataSource
import com.mobile.pablo.storage.source.search.SearchDataStorage
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class VideosUseCaseTest {

    private lateinit var searchDataSource: SearchDataSource
    private lateinit var searchDataStorage: SearchDataStorage
    private lateinit var popularDataSource: PopularDataSource

    private val searchMapper = SearchMapper(
        PageInfoMapper(),
        SearchItemMapper(
            IdMapper(),
            SearchSnippetMapper(
                ThumbnailsMapper(
                    ThumbnailMapper()
                )
            )
        )
    )

    private val popularItemMapper = PopularItemMapper(
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
        searchDataStorage = mockk()
        popularDataSource = mockk()
    }

    @Test
    fun getSearchVideos_returnsSearchWhenSearchResponseIsSuccessful() = runTest {
        coEvery { searchDataSource.getSearchVideos("dog") } returns DataTransfer(MOCK_DOG_SEARCH)
        coEvery { searchDataStorage.insertSearch(MOCK_DOG_SEARCH) } returns Unit

        val getSearchVideos = VideosUseCase.GetSearchVideos(
            searchDataSource = searchDataSource,
            searchDataStorage = searchDataStorage,
            searchMapper = searchMapper
        )
        val result = getSearchVideos("dog")

        assertThat(result.isSuccessful).isTrue()
        assertThat(result.data).isEqualTo(searchMapper.map(MOCK_DOG_SEARCH))
    }

    @Test
    fun getSearchVideos_returnsLocalSearchWhenSearchResponseIsNotSuccessful() = runTest {

        coEvery { searchDataSource.getSearchVideos("dog") } returns DataTransfer(error = Exception())
        coEvery { searchDataStorage.getSearch() } returns MOCK_DOG_SEARCH
        val getSearchVideos = VideosUseCase.GetSearchVideos(
            searchDataSource = searchDataSource,
            searchDataStorage = searchDataStorage,
            searchMapper = searchMapper
        )

        val result = getSearchVideos("dog")

        assertThat(result.isSuccessful).isTrue()
        assertThat(result.data).isEqualTo(searchMapper.map(MOCK_DOG_SEARCH))
    }

    @Test
    fun getSearchVideos_returnsErrorWhenSearchResponseAndLocalSearchAreNotSuccessful() = runTest {
        coEvery { searchDataSource.getSearchVideos("dog") } returns DataTransfer(error = Exception())
        coEvery { searchDataStorage.getSearch() } throws Exception()

        val getSearchVideos = VideosUseCase.GetSearchVideos(
            searchDataSource = searchDataSource,
            searchDataStorage = searchDataStorage,
            searchMapper = searchMapper
        )
        val result = getSearchVideos("dog")

        assertThat(result.isSuccessful).isFalse()
        assertThat(result.error).isNotNull()
    }

    @Test
    fun getPopularVideos_pagingConfigReturnsGivenItem() = runTest {
        coEvery { popularDataSource.getPopularVideos("EN", "CAUSAS") } returns
                DataTransfer(MOCK_POPULAR_ITEM)

        val getPopularVideos = VideosUseCase.GetPopularVideos(
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
    fun getPopularVideos_pagingConfigReturnsEmptyData() = runTest {
        coEvery { popularDataSource.getPopularVideos("EN", "CAUSAS") } returns
                DataTransfer()

        val getPopularVideos = VideosUseCase.GetPopularVideos(
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