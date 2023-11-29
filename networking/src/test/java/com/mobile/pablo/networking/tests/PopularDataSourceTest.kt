package com.mobile.pablo.networking.tests

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.util.TEXT_PLAIN
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
import com.mobile.pablo.networking.model.popular.PopularResponse
import com.mobile.pablo.networking.service.YoutubeService
import com.mobile.pablo.networking.source.popular.PopularDataSource
import com.mobile.pablo.networking.source.popular.PopularDataSourceImpl
import io.mockk.coEvery
import io.mockk.mockk
import java.net.HttpURLConnection
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class PopularDataSourceTest {
    private lateinit var youtubeService: YoutubeService
    private lateinit var popularDataSource: PopularDataSource
    private val popularResponseMapper: PopularResponseMapper =
        PopularResponseMapper(
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
        youtubeService = mockk()
        popularDataSource = PopularDataSourceImpl(youtubeService, popularResponseMapper)
    }

    @Test
    fun `getPopularVideos returns mapped data when response is successful`() =
        runBlocking {
            coEvery {
                youtubeService.getPopularSearchVideos(
                    regionCode = "US",
                    pageToken = "CAoQAA"
                )
            } returns Response.success(MOCK_POPULAR_ITEM)
            val result = popularDataSource.getPopularVideos("US", "CAoQAA")
            val wantedResult = DataTransfer(popularResponseMapper.map(MOCK_POPULAR_ITEM))
            assertThat(result.data).isEqualTo(wantedResult.data)
        }

    @Test
    fun `getPopularVideos returns network exception when response is not successful`() =
        runBlocking {
            val notFound = "Not found"

            val error =
                Response.error<PopularResponse>(
                    HttpURLConnection.HTTP_UNSUPPORTED_TYPE,
                    notFound.toResponseBody(TEXT_PLAIN.toMediaTypeOrNull())
                )

            coEvery { youtubeService.getPopularSearchVideos(regionCode = "US", pageToken = "CAoQAA") } returns error

            val result = popularDataSource.getPopularVideos("US", "CAoQAA")

            assertThat(result.error!!.message).isEqualTo(HttpException(error).message)
            assertThat(result.data).isNull()
        }
}
