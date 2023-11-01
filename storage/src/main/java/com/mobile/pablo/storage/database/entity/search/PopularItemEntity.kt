package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_item")
internal data class PopularItemEntity(
    val kind: String? = null,
    @PrimaryKey val etag: String = "",
    val id: String? = null,
    @Embedded(prefix = "snippet_") val snippet: PopularSnippetEntity? = null
)