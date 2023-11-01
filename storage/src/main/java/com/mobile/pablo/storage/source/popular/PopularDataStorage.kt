package com.mobile.pablo.storage.source.popular

import com.mobile.pablo.core.model.popular.PopularItemDTO

interface PopularDataStorage {

    suspend fun insertPopularItem(search: PopularItemDTO)
    suspend fun insertPopularItems(items: List<PopularItemDTO>)
    suspend fun getPopularItems(): List<PopularItemDTO>?
    suspend fun removePopularItem(etag: String)
    suspend fun clearPopularItems()
    suspend fun getPopularItemsByEtag(etag: String): PopularItemDTO?
    suspend fun updatePopularItemsByEtag(etag: String)
}