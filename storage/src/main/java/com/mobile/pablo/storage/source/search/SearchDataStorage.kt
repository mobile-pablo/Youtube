package com.mobile.pablo.storage.source.search

import com.mobile.pablo.core.model.search.SearchDTO

interface SearchDataStorage {
    suspend fun upsertSearch(search: SearchDTO?)

    suspend fun getSearch(): SearchDTO?

    suspend fun removeSearch(etag: String)

    suspend fun clearSearches()

    suspend fun getSearchByEtag(etag: String): SearchDTO?
}
