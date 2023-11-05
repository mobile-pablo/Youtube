package com.mobile.pablo.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobile.pablo.storage.database.dao.PopularDao
import com.mobile.pablo.storage.database.dao.SearchDao
import com.mobile.pablo.storage.database.entity.popular.PopularEntity
import com.mobile.pablo.storage.database.entity.search.SearchEntity
import com.mobile.pablo.storage.database.typeconverter.ListConverter

@Database(
    entities = [SearchEntity::class, PopularEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ListConverter::class
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao
    abstract fun popularDao(): PopularDao

    companion object {

        private const val DB_NAME = "app_database.db"
        private lateinit var instance: AppDatabase

        fun getInstance(context: Context): AppDatabase =
            if (this::instance.isInitialized) instance
            else
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .apply {
                        instance = this
                    }
    }
}