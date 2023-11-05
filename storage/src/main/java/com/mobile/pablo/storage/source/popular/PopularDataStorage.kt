package com.mobile.pablo.storage.source.popular

import com.mobile.pablo.core.model.popular.PopularDTO

interface PopularDataStorage {

    suspend fun insertPopular(search: PopularDTO?)
    suspend fun getPopular(): PopularDTO?
    suspend fun removePopular(etag: String)
    suspend fun clearPopulars()
    suspend fun getPopularByEtag(etag: String): PopularDTO?
}