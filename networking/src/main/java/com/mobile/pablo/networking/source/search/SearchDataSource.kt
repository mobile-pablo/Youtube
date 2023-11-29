package com.mobile.pablo.networking.source.search

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.model.search.SearchDTO

interface SearchDataSource {
    suspend fun getSearchVideos(query: String): DataTransfer<SearchDTO>
}
