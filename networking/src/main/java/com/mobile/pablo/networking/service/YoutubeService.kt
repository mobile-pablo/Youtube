package com.mobile.pablo.networking.service

import com.mobile.pablo.networking.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface YoutubeService {

    @GET("/search")
    suspend fun getSearchVideos(
        @Query("part") part: String = "snippet",
        @Query("q") q: String = "cat",
    ) : Response<SearchResponse>
}