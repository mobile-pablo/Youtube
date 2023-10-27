package com.mobile.pablo.networking.source

import com.mobile.pablo.core.model.SearchDTO
import com.mobile.pablo.core.data.DataTransfer

interface VideoDataSource {

    suspend fun getSearchVideos(query: String): DataTransfer<SearchDTO>

    suspend fun getPopularVideos(
        query: String,
        regionCode: String
    ): DataTransfer<SearchDTO>
}