package com.mobile.pablo.storage.database.typeconverter

import androidx.room.TypeConverter

internal object ListConverter {

    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString(separator = ",")

    @TypeConverter
    fun toList(rawList: String): List<String> = rawList.split(",")
}