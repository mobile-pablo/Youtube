package com.mobile.pablo.storage.source.search

import com.mobile.pablo.core.model.search.SearchDTO
import com.mobile.pablo.storage.database.dao.SearchDao
import com.mobile.pablo.storage.mapper.search.SearchEntityMapper
import javax.inject.Inject

internal class SearchDataStorageImpl
    @Inject
    constructor(
        private val searchDao: SearchDao,
        private val searchEntityMapper: SearchEntityMapper
    ) : SearchDataStorage {
        override suspend fun insertSearch(search: SearchDTO?) {
            val entity = searchEntityMapper.map(search)!!
            searchDao.insertSearchWithItems(entity)
        }

        override suspend fun getSearch(): SearchDTO? {
            val items = searchDao.getSearchWithItems()
            return searchEntityMapper.map(items)
        }

        override suspend fun removeSearch(etag: String) = searchDao.removeSearchWithItems(etag)

        override suspend fun clearSearches() = searchDao.clearSearchesWithItems()

        override suspend fun getSearchByEtag(etag: String): SearchDTO? {
            val entity = searchDao.getSearchWithItemsByEtag(etag)
            return searchEntityMapper.map(entity)
        }
    }
