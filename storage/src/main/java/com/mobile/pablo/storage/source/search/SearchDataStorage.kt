package com.mobile.pablo.storage.source.search

import com.mobile.pablo.core.model.search.SearchDTO

interface SearchDataStorage {

    suspend fun insertSearch(search: SearchDTO?)
    suspend fun insertSearches(items: List<SearchDTO?>)
    suspend fun getSearch(): SearchDTO?
    suspend fun removeSearch(etag: String)
    suspend fun clearSearches()
    suspend fun getSearchByEtag(etag: String): SearchDTO?
    suspend fun updateItemsByEtag(etag: String)
}