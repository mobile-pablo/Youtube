package com.mobile.pablo.networking.source

import com.mobile.pablo.core.models.SearchDTO
import com.mobile.pablo.core.data.DataTransfer

interface VideoDataSource {

    suspend fun getSearchVideos(query : String) : DataTransfer<SearchDTO>
}