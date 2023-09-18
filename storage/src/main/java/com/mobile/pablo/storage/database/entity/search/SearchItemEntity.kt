package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_item")
internal data class SearchItemEntity(
    @PrimaryKey(autoGenerate = true) val primaryId: Int = 0,
    val kind: String,
    val etag: String,
    @Embedded(prefix = "id_") val id: SearchItemIdEntity,
    @Embedded(prefix = "snippet_") val snippet: SearchItemSnippetEntity
)