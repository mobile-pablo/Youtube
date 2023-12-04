package com.mobile.pablo.storage.tests

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.storage.database.dao.SearchDao
import com.mobile.pablo.storage.mapper.common.IdEntityMapper
import com.mobile.pablo.storage.mapper.common.PageInfoEntityMapper
import com.mobile.pablo.storage.mapper.common.ThumbnailEntityMapper
import com.mobile.pablo.storage.mapper.common.ThumbnailsEntityMapper
import com.mobile.pablo.storage.mapper.search.SearchEntityMapper
import com.mobile.pablo.storage.mapper.search.SearchItemEntityMapper
import com.mobile.pablo.storage.mapper.search.SnippetEntityMapper
import com.mobile.pablo.storage.mocks.MOCK_DOG_SEARCH
import com.mobile.pablo.storage.source.search.SearchDataStorage
import com.mobile.pablo.storage.source.search.SearchDataStorageImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchDataStorageTest {
    private lateinit var searchDao: SearchDao
    private lateinit var searchDataStorage: SearchDataStorage
    private val searchEntityMapper: SearchEntityMapper =
        SearchEntityMapper(
            PageInfoEntityMapper(),
            SearchItemEntityMapper(
                IdEntityMapper(),
                SnippetEntityMapper(
                    ThumbnailsEntityMapper(
                        ThumbnailEntityMapper()
                    )
                )
            )
        )

    @Before
    fun setUp() {
        searchDao = mockk()
        searchDataStorage = SearchDataStorageImpl(searchDao, searchEntityMapper)
    }

    @Test
    fun `insertSearch inserts search into database`() =
        runBlocking {
            val searchDTO = searchEntityMapper.map(MOCK_DOG_SEARCH)!!
            coEvery { searchDao.upsertSearchWithItems(MOCK_DOG_SEARCH) } just runs
            searchDataStorage.upsertSearch(searchDTO)
            coVerify { searchDao.upsertSearchWithItems(MOCK_DOG_SEARCH) }
        }

    @Test
    fun `getSearch retrieves search from database`() =
        runBlocking {
            coEvery { searchDao.getSearchWithItems() } returns MOCK_DOG_SEARCH
            val result = searchDataStorage.getSearch()
            val searchDTO = searchEntityMapper.map(MOCK_DOG_SEARCH)
            assertThat(result).isEqualTo(searchDTO)
        }

    @Test
    fun `removeSearch removes search from database`() =
        runBlocking {
            val etag = "etag"
            coEvery { searchDao.removeSearchWithItems(etag) } just runs
            searchDataStorage.removeSearch(etag)
            coVerify { searchDao.removeSearchWithItems(etag) }
        }

    @Test
    fun `clearSearches clears all searches from database`() =
        runBlocking {
            coEvery { searchDao.clearSearchesWithItems() } just runs
            searchDataStorage.clearSearches()
            coVerify { searchDao.clearSearchesWithItems() }
        }

    @Test
    fun `getSearchByEtag retrieves search by etag from database`() =
        runBlocking {
            val etag = "etag"
            coEvery { searchDao.getSearchWithItemsByEtag(etag) } returns MOCK_DOG_SEARCH
            val result = searchDataStorage.getSearchByEtag(etag)
            val searchDTO = searchEntityMapper.map(MOCK_DOG_SEARCH)
            assertThat(result).isEqualTo(searchDTO)
        }
}
