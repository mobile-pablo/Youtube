package com.mobile.pablo.storage.source.popular

import com.mobile.pablo.core.model.popular.PopularDTO
import com.mobile.pablo.storage.database.dao.PopularDao
import com.mobile.pablo.storage.mapper.popular.PopularEntityMapper
import javax.inject.Inject

internal class PopularDataStorageImpl @Inject constructor(
    private val popularDao: PopularDao,
    private val popularEntityMapper: PopularEntityMapper
) : PopularDataStorage {

    override suspend fun upsertPopular(search: PopularDTO?) {
        val entity = popularEntityMapper.map(search)!!
        popularDao.upsertPopularWithItems(entity)
    }

    override suspend fun getPopular(): PopularDTO? {
        val items = popularDao.getPopularWithItems()
        return popularEntityMapper.map(items)
    }

    override suspend fun removePopular(etag: String) = popularDao.removePopularWithItems(etag)

    override suspend fun clearPopulars() = popularDao.clearPopularsWithItems()

    override suspend fun getPopularByEtag(etag: String): PopularDTO? {
        val entity = popularDao.getPopularWithItemsByEtag(etag)
        return popularEntityMapper.map(entity)
    }
}