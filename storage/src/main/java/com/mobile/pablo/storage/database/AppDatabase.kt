package com.mobile.pablo.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobile.pablo.storage.database.entity.search.SearchItemEntity

@Database(
    entities = [SearchItemEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {

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