package com.mobile.pablo.storage.source.popular

import androidx.paging.PagingSource
import com.mobile.pablo.core.model.popular.PopularDTO

interface PopularDataStorage {

    suspend fun upsertPopular(search: PopularDTO?)
    suspend fun getPopular(): PopularDTO?
    suspend fun removePopular(etag: String)
    suspend fun clearPopulars()
    suspend fun getPopularByEtag(etag: String): PopularDTO?
}