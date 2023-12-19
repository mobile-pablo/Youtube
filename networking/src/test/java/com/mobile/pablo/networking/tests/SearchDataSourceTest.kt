package com.mobile.pablo.networking.tests

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.core.util.TEXT_PLAIN
import com.mobile.pablo.networking.mapper.common.IdResponseMapper
import com.mobile.pablo.networking.mapper.common.PageInfoResponseMapper
import com.mobile.pablo.networking.mapper.common.ThumbnailResponseMapper
import com.mobile.pablo.networking.mapper.common.ThumbnailsResponseMapper
import com.mobile.pablo.networking.mapper.search.SearchItemResponseMapper
import com.mobile.pablo.networking.mapper.search.SearchResponseMapper
import com.mobile.pablo.networking.mapper.search.SearchSnippetResponseMapper
import com.mobile.pablo.networking.mocks.MOCK_DOG_SEARCH
import com.mobile.pablo.networking.model.search.SearchResponse
import com.mobile.pablo.networking.service.YoutubeService
import com.mobile.pablo.networking.source.search.SearchDataSource
import com.mobile.pablo.networking.source.search.SearchDataSourceImpl
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

class SearchDataSourceTest {

    private lateinit var youtubeService: YoutubeService
    private lateinit var searchDataSource: SearchDataSource
    private val searchResponseMapper: SearchResponseMapper =
        SearchResponseMapper(
            PageInfoResponseMapper(),
            SearchItemResponseMapper(
                IdResponseMapper(),
                SearchSnippetResponseMapper(
                    ThumbnailsResponseMapper(
                        ThumbnailResponseMapper()
                    )
                )
            )
        )

    @Before
    fun setUp() {
        youtubeService = mockk()
        searchDataSource = SearchDataSourceImpl(youtubeService, searchResponseMapper)
    }

    @Test
    fun `getSearchVideos returns mapped data when response is successful`() =
        runBlocking {
            val query = "test"
            coEvery { youtubeService.getSearchVideos(q = query, pageToken = EMPTY_STRING) } returns
                Response.success(MOCK_DOG_SEARCH)
            val result = searchDataSource.getSearchVideos(query, EMPTY_STRING)
            val wantedResult = DataTransfer(searchResponseMapper.map(MOCK_DOG_SEARCH))
            assertThat(result.data).isEqualTo(wantedResult.data)
        }

    @Test
    fun `getSearchVideos returns network exception when response is not successful`() =
        runBlocking {
            val query = "dog"
            val notFound = "Not found"

            val error =
                Response.error<SearchResponse>(
                    HttpURLConnection.HTTP_UNSUPPORTED_TYPE,
                    notFound.toResponseBody(TEXT_PLAIN.toMediaTypeOrNull())
                )

            coEvery { youtubeService.getSearchVideos(q = query, pageToken = EMPTY_STRING) } returns error

            val result = searchDataSource.getSearchVideos(query, EMPTY_STRING)

            assertThat(result.error!!.message).isEqualTo(HttpException(error).message)
            assertThat(result.data).isNull()
        }
}
