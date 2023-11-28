package com.mobile.pablo.storage.sharedprefs

interface SharedPreferencesManager {
    suspend fun getString(setting: Setting): String?

    suspend fun setString(setting: Setting, value: String): String

    suspend fun getInt(setting: Setting): Int

    suspend fun setInt(setting: Setting, value: Int): Int

    suspend fun getLong(setting: Setting): Long

    suspend fun setLong(setting: Setting, value: Long): Long

    suspend fun getBool(setting: Setting): Boolean

    suspend fun setBool(setting: Setting, value: Boolean): Boolean

    suspend fun clear()
}
