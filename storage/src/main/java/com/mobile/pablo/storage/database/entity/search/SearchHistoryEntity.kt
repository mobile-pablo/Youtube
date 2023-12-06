package com.mobile.pablo.storage.database.entity.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobile.pablo.storage.database.const.SEARCH_HISTORY_TABLE_NAME

@Entity(tableName = SEARCH_HISTORY_TABLE_NAME)
internal data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val query: String
)
