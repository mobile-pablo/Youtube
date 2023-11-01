package com.mobile.pablo.networking.service

import com.mobile.pablo.networking.model.PopularVideosResponse
import com.mobile.pablo.networking.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface YoutubeService {

    @GET("youtube//v3/search")
    suspend fun getSearchVideos(
        @Query("part") part: String = "snippet",
        @Query("q") q: String
    ): Response<SearchResponse>

    @GET("youtube/v3/videos")
    suspend fun getPopularSearchVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = "US"
    ): Response<PopularVideosResponse>
}