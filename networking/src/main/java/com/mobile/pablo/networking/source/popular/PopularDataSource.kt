package com.mobile.pablo.networking.source.popular

import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.model.popular.PopularDTO

interface PopularDataSource {
    suspend fun getPopularVideos(
        regionCode: String,
        pageToken: String?
    ): DataTransfer<PopularDTO>
}
