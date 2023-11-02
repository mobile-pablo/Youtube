package com.mobile.pablo.storage.source.popular

import com.mobile.pablo.core.model.popular.PopularItemDTO
import com.mobile.pablo.storage.database.dao.PopularDao
import com.mobile.pablo.storage.mapper.popular.PopularEntityMapper
import javax.inject.Inject

internal class PopularDataStorageImpl @Inject constructor(
    private val popularDao: PopularDao,
    private val popularEntityMapper: PopularEntityMapper
) : PopularDataStorage {

    override suspend fun insertPopularItem(search: PopularItemDTO?) {
        val entity = popularEntityMapper.map(search)!!
        popularDao.insertPopularItem(entity)
    }

    override suspend fun insertPopularItems(items: List<PopularItemDTO?>) {
        val entities = items.map(popularEntityMapper::map)
        popularDao.insertPopularItems(entities)
    }

    override suspend fun getPopularItems(): List<PopularItemDTO> {
        val items = popularDao.getPopularItems()
        return items!!.map { popularEntityMapper.map(it)!! }
    }

    override suspend fun removePopularItem(etag: String) = popularDao.removePopularItem(etag)

    override suspend fun clearPopularItems() = popularDao.clearPopularItems()

    override suspend fun getPopularItemsByEtag(etag: String): PopularItemDTO? {
        val entity = popularDao.getPopularItemsByEtag(etag)
        return popularEntityMapper.map(entity)
    }

    override suspend fun updatePopularItemsByEtag(etag: String) =
        popularDao.updatePopularItemsByEtag(etag)
}