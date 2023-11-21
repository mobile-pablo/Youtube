package com.mobile.pablo.storage.tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.storage.database.AppDatabase
import com.mobile.pablo.storage.database.dao.SearchDao
import com.mobile.pablo.storage.mocks.MOCK_DOG_SEARCH
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchDaoTest {

    private lateinit var searchDao: SearchDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        searchDao = appDatabase.searchDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertSearchWithItemsAreStored() = runBlocking {
        searchDao.insertSearchWithItems(MOCK_DOG_SEARCH)
        val result = searchDao.getSearchWithItems()
        assertThat(result).isEqualTo(MOCK_DOG_SEARCH)
    }

    @Test
    fun removeSearchWithItemsAreRemoved() = runBlocking {
        searchDao.insertSearchWithItems(MOCK_DOG_SEARCH)
        searchDao.removeSearchWithItems(MOCK_DOG_SEARCH.search!!.etag)
        val result = searchDao.getSearchWithItems()
        assertThat(result?.search).isNull()
        assertThat(result?.items).isNull()
    }

    @Test
    fun clearSearchesWithItemsAreCleared() = runBlocking {
        searchDao.insertSearchWithItems(MOCK_DOG_SEARCH)
        searchDao.clearSearchesWithItems()
        val result = searchDao.getSearchWithItems()
        assertThat(result?.search).isNull()
        assertThat(result?.items).isNull()
    }
}