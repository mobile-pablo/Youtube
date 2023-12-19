package com.mobile.pablo.networking.service

import androidx.annotation.Keep
import com.mobile.pablo.networking.const.CHART_QUERY
import com.mobile.pablo.networking.const.CONTENT_DETAILS
import com.mobile.pablo.networking.const.MOST_POPULAR
import com.mobile.pablo.networking.const.PAGE_TOKEN_QUERY
import com.mobile.pablo.networking.const.PART_QUERY
import com.mobile.pablo.networking.const.Q_QUERY
import com.mobile.pablo.networking.const.REGION_CODE_QUERY
import com.mobile.pablo.networking.const.REGION_US
import com.mobile.pablo.networking.const.SNIPPET
import com.mobile.pablo.networking.const.STATISTICS
import com.mobile.pablo.networking.const.YOUTUBE_V3_SEARCH
import com.mobile.pablo.networking.const.YOUTUBE_V3_VIDEOS
import com.mobile.pablo.networking.model.popular.PopularResponse
import com.mobile.pablo.networking.model.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@Keep
internal interface YoutubeService {
    @GET(YOUTUBE_V3_SEARCH)
    suspend fun getSearchVideos(
        @Query(PART_QUERY) part: String = SNIPPET,
        @Query(Q_QUERY) q: String,
        @Query(PAGE_TOKEN_QUERY) pageToken: String? = null
    ): Response<SearchResponse>

    @GET(YOUTUBE_V3_VIDEOS)
    suspend fun getPopularSearchVideos(
        @Query(PART_QUERY) part: String = "$SNIPPET, $STATISTICS, $CONTENT_DETAILS",
        @Query(CHART_QUERY) chart: String = MOST_POPULAR,
        @Query(REGION_CODE_QUERY) regionCode: String = REGION_US,
        @Query(PAGE_TOKEN_QUERY) pageToken: String? = null
    ): Response<PopularResponse>
}
