package com.mobile.pablo.storage.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class SharedPreferencesManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SharedPreferencesManager {

    companion object {

        const val SHARED_PREF_NAME = "youtube_settings"
    }

    private val sharedPreferences: SharedPreferences

    init {
        val masterKey =
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        sharedPreferences =
            EncryptedSharedPreferences.create(
                context,
                SHARED_PREF_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    }

    override suspend fun getString(setting: Setting): String? {
        if (setting.type != Type.STRING) throw IllegalArgumentException("${setting.name} is not a String")
        return sharedPreferences.getString(setting.name, setting.type.default as String)
    }

    override suspend fun setString(
        setting: Setting,
        value: String
    ): String {
        if (setting.type != Type.STRING) throw IllegalArgumentException("${setting.name} is not a String")
        sharedPreferences.edit()
            .putString(setting.name, value)
            .apply()

        return value
    }

    override suspend fun getInt(setting: Setting): Int {
        if (setting.type != Type.INT) throw IllegalArgumentException("${setting.name} is not an Int")
        return sharedPreferences.getInt(setting.name, setting.type.default as Int)
    }

    override suspend fun setInt(
        setting: Setting,
        value: Int
    ): Int {
        if (setting.type != Type.INT) throw IllegalArgumentException("${setting.name} is not an Int")
        sharedPreferences.edit()
            .putInt(setting.name, value)
            .apply()

        return value
    }

    override suspend fun getLong(setting: Setting): Long {
        if (setting.type != Type.LONG) throw IllegalArgumentException("${setting.name} is not a Long")
        return sharedPreferences.getLong(setting.name, setting.type.default as Long)
    }

    override suspend fun setLong(
        setting: Setting,
        value: Long
    ): Long {
        if (setting.type != Type.LONG) throw IllegalArgumentException("${setting.name} is not a Long")
        sharedPreferences.edit()
            .putLong(setting.name, value)
            .apply()

        return value
    }

    override suspend fun getBool(setting: Setting): Boolean {
        if (setting.type != Type.BOOL) throw IllegalArgumentException("${setting.name} is not a Boolean")
        return sharedPreferences.getBoolean(setting.name, setting.type.default as Boolean)
    }

    override suspend fun setBool(
        setting: Setting,
        value: Boolean
    ): Boolean {
        if (setting.type != Type.BOOL) throw IllegalArgumentException("${setting.name} is not a Boolean")
        sharedPreferences.edit()
            .putBoolean(setting.name, value)
            .apply()
        return value
    }

    override suspend fun clear() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }
}
