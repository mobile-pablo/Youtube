package com.mobile.pablo.storage.sharedprefs

interface SharedPreferencesManager {
    suspend fun getString(setting: Setting): String
    suspend fun setString(setting: Setting, value: String)
    suspend fun getInt(setting: Setting): Int
    suspend fun setInt(setting: Setting, value: Int)
    suspend fun getLong(setting: Setting): Long
    suspend fun setLong(setting: Setting, value: Long)
    suspend fun getBool(setting: Setting): Boolean
    suspend fun setBool(setting: Setting, value: Boolean)
    suspend fun clear()
}