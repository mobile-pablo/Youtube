package com.mobile.pablo.storage.source.search

import com.mobile.pablo.core.model.search.SearchItemDTO
import com.mobile.pablo.storage.database.dao.SearchDao
import com.mobile.pablo.storage.mapper.search.SearchItemEntityMapper
import javax.inject.Inject

internal class SearchDataStorageImpl @Inject constructor(
    private val searchDao: SearchDao,
    private val searchItemEntityMapper: SearchItemEntityMapper
) : SearchDataStorage {

    override suspend fun insertSearchItem(search: SearchItemDTO?) {
        val entity = searchItemEntityMapper.map(search)!!
        searchDao.insertSearchItem(entity)
    }

    override suspend fun insertSearchItems(items: List<SearchItemDTO?>) {
        val entities = items.map(searchItemEntityMapper::map)
        searchDao.insertSearchItems(entities)
    }

    override suspend fun getSearchItems(): List<SearchItemDTO> {
        val items = searchDao.getSearchItems()
        return items!!.map { searchItemEntityMapper.map(it)!! }
    }

    override suspend fun removeSearchItem(etag: String) = searchDao.removeSearchItem(etag)

    override suspend fun clearSearchItems() = searchDao.clearSearchItems()

    override suspend fun getSearchItemsByEtag(etag: String): SearchItemDTO? {
        val entity = searchDao.getSearchItemsByEtag(etag)
        return searchItemEntityMapper.map(entity)
    }

    override suspend fun updateSearchItemsByEtag(etag: String) = searchDao.updateSearchItemsByEtag(etag)
}