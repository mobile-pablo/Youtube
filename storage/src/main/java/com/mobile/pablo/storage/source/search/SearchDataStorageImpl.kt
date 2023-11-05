package com.mobile.pablo.storage.source.search

import com.mobile.pablo.core.model.search.SearchDTO
import com.mobile.pablo.storage.database.dao.SearchDao
import com.mobile.pablo.storage.mapper.search.SearchEntityMapper
import javax.inject.Inject

internal class SearchDataStorageImpl @Inject constructor(
    private val searchDao: SearchDao,
    private val searchEntityMapper: SearchEntityMapper
) : SearchDataStorage {

    override suspend fun insertSearch(search: SearchDTO?) {
        val entity = searchEntityMapper.map(search)!!
        searchDao.insertSearch(entity)
    }

    override suspend fun insertSearches(items: List<SearchDTO?>) {
        val entities = items.map(searchEntityMapper::map)
        searchDao.insertSearches(entities)
    }

    override suspend fun getSearch(): SearchDTO? {
        val items = searchDao.getSearch()
        return searchEntityMapper.map(items)
    }

    override suspend fun removeSearch(etag: String) = searchDao.removeSearch(etag)

    override suspend fun clearSearches() = searchDao.clearSearches()

    override suspend fun getSearchByEtag(etag: String): SearchDTO? {
        val entity = searchDao.getSearchByEtag(etag)
        return searchEntityMapper.map(entity)
    }

    override suspend fun updateItemsByEtag(etag: String) = searchDao.updateSearchByEtag(etag)
}