package com.mobile.pablo.storage.source.popular

import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.storage.database.dao.PopularDao
import com.mobile.pablo.storage.mapper.popular.PopularEntityMapper
import javax.inject.Inject

internal class PopularDataStorageImpl @Inject constructor(
    private val popularDao: PopularDao,
    private val popularEntityMapper: PopularEntityMapper
) : PopularDataStorage {

    override suspend fun insertPopular(search: PopularDTO?) {
        val entity = popularEntityMapper.map(search)!!
        popularDao.insertPopular(entity)
    }

    override suspend fun insertPopulars(items: List<PopularDTO?>) {
        val entities = items.map(popularEntityMapper::map)
        popularDao.insertPopulars(entities)
    }

    override suspend fun getPopular(): PopularDTO? {
        val items = popularDao.getPopular()
        return popularEntityMapper.map(items)
    }

    override suspend fun removePopular(etag: String) = popularDao.removePopular(etag)

    override suspend fun clearPopulars() = popularDao.clearPopulars()

    override suspend fun getPopularByEtag(etag: String): PopularDTO? {
        val entity = popularDao.getPopularByEtag(etag)
        return popularEntityMapper.map(entity)
    }

    override suspend fun updatePopularByEtag(etag: String) =
        popularDao.updatePopularByEtag(etag)
}