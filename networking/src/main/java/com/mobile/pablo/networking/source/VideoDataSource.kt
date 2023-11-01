package com.mobile.pablo.networking.source

import com.mobile.pablo.core.model.search.SearchDTO
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.model.popular.PopularDTO

interface VideoDataSource {

    suspend fun getSearchVideos(query: String): DataTransfer<SearchDTO>

    suspend fun getPopularVideos(regionCode: String): DataTransfer<PopularDTO>
}