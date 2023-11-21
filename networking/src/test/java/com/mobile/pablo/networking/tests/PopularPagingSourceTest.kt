package com.mobile.pablo.networking.tests

import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Page
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.networking.mapper.common.ContentDetailsResponseMapper
import com.mobile.pablo.networking.mapper.common.LocalizedResponseMapper
import com.mobile.pablo.networking.mapper.common.PageInfoResponseMapper
import com.mobile.pablo.networking.mapper.common.StatisticsResponseMapper
import com.mobile.pablo.networking.mapper.common.ThumbnailResponseMapper
import com.mobile.pablo.networking.mapper.common.ThumbnailsResponseMapper
import com.mobile.pablo.networking.mapper.popular.PopularItemResponseMapper
import com.mobile.pablo.networking.mapper.popular.PopularResponseMapper
import com.mobile.pablo.networking.mapper.popular.PopularSnippetResponseMapper
import com.mobile.pablo.networking.mocks.MOCK_POPULAR_ITEM
import com.mobile.pablo.networking.source.popular.PopularDataSource
import com.mobile.pablo.networking.source.popular.PopularPagingSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PopularPagingSourceTest {

    private lateinit var popularDataSource: PopularDataSource
    private lateinit var popularPagingSource: PopularPagingSource
    private val popularResponseMapper: PopularResponseMapper = PopularResponseMapper(
        PageInfoResponseMapper(),
        PopularItemResponseMapper(
            PopularSnippetResponseMapper(
                ThumbnailsResponseMapper(
                    ThumbnailResponseMapper()
                ),
                LocalizedResponseMapper()
            ),
            ContentDetailsResponseMapper(),
            StatisticsResponseMapper()
        )
    )

    @Before
    fun setUp() {
        popularDataSource = mockk()
        popularPagingSource = PopularPagingSource(popularDataSource)
    }

    @Test
    fun `load returns LoadResult Page when data is not null`() = runBlocking {
        val popularDTO = popularResponseMapper.map(MOCK_POPULAR_ITEM)
        val dataTransfer = DataTransfer(data = popularDTO)

        coEvery { popularDataSource.getPopularVideos(any(), any()) } returns dataTransfer

        val result = popularPagingSource.load(Refresh(null, 0, false))

        assertThat(result).isInstanceOf(Page::class.java)
    }

    @Test
    fun `load returns LoadResult Error when data is null`() = runBlocking {
        val dataTransfer = DataTransfer<PopularDTO>(error = Exception())

        coEvery { popularDataSource.getPopularVideos(any(), any()) } returns dataTransfer

        val result = popularPagingSource.load(Refresh(null, 0, false))

        assertThat(result).isInstanceOf(Error::class.java)
    }
}