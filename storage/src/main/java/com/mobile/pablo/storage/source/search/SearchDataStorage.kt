package com.mobile.pablo.storage.source.search

import com.mobile.pablo.core.model.SearchItemDTO

interface SearchDataStorage {

    suspend fun insertSearchItem(search: SearchItemDTO)
    suspend fun insertSearchItems(items: List<SearchItemDTO>)
    suspend fun getSearchItems(): List<SearchItemDTO>?
    suspend fun removeSearchItem(etag: String)
    suspend fun clearSearchItems()
    suspend fun getSearchItemsByEtag(etag: String): SearchItemDTO?
    suspend fun updateSearchItemsByEtag(etag: String)
}