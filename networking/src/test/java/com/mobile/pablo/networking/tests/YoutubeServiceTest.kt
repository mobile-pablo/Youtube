package com.mobile.pablo.networking.tests

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.networking.const.TIMEOUT_MILLIS
import com.mobile.pablo.networking.interceptor.RequestInterceptor
import com.mobile.pablo.networking.mocks.MOCK_DOG_SEARCH
import com.mobile.pablo.networking.mocks.MOCK_POPULAR_ITEM
import com.mobile.pablo.networking.model.popular.PopularResponse
import com.mobile.pablo.networking.model.search.SearchResponse
import com.mobile.pablo.networking.service.YoutubeService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class YoutubeServiceTest {

    private var mockWebServer = MockWebServer()

    private lateinit var youtubeService: YoutubeService
    private val okHttpClient =
        OkHttpClient.Builder()
            .callTimeout(TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Before
    fun setUp() {
        mockWebServer.start()

        youtubeService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(YoutubeService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetSearchVideos() = runBlocking {
        val jsonAdapter = moshi.adapter(SearchResponse::class.java)

        val responseJson = jsonAdapter.toJson(MOCK_DOG_SEARCH)
        val response = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(responseJson)

        mockWebServer.enqueue(response)
        val result = youtubeService.getSearchVideos(q = "dog")
        val responseFromJson = jsonAdapter.fromJson(responseJson)

        assertThat(result.isSuccessful).isTrue()
        assertThat(result.body()).isEqualTo(responseFromJson)
    }

    @Test
    fun testGetPopularVideos() = runBlocking {
        val jsonAdapter = moshi.adapter(PopularResponse::class.java)

        val responseJson = jsonAdapter.toJson(MOCK_POPULAR_ITEM)
        val response = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(responseJson)

        mockWebServer.enqueue(response)
        val result = youtubeService.getPopularSearchVideos()
        val responseFromJson = jsonAdapter.fromJson(responseJson)

        assertThat(result.isSuccessful).isTrue()
        assertThat(result.body()).isEqualTo(responseFromJson)
    }
}