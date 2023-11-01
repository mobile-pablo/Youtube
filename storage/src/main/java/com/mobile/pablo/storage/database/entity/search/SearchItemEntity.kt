package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_item")
internal data class SearchItemEntity(
    val kind: String,
    @PrimaryKey val etag: String,
    @Embedded(prefix = "id_") val id: IdEntity,
    @Embedded(prefix = "snippet_") val snippet: SearchSnippetEntity
)