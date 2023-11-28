package com.mobile.pablo.storage.tests

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.storage.sharedprefs.Setting
import com.mobile.pablo.storage.sharedprefs.SharedPreferencesManager
import com.mobile.pablo.storage.sharedprefs.SharedPreferencesManagerImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferencesManagerTest {
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        sharedPreferencesManager = SharedPreferencesManagerImpl(context)
    }

    // String
    @Test
    fun setString_savesValueToSharedPreferences() =
        runBlocking {
            val setting = Setting.TEST_STRING
            sharedPreferencesManager.setString(setting, "value")
            val result = sharedPreferencesManager.getString(setting)
            assertThat(result).isEqualTo("value")
        }

    @Test(expected = IllegalArgumentException::class)
    fun getString_throwsIllegalArgumentException_whenSettingTypeIsNotString(): Unit =
        runBlocking {
            val setting = Setting.TEST_BOOL
            sharedPreferencesManager.getString(setting)
        }

    @Test(expected = IllegalArgumentException::class)
    fun setString_throwsIllegalArgumentException_whenSettingTypeIsNotString(): Unit =
        runBlocking {
            val setting = Setting.TEST_BOOL
            sharedPreferencesManager.setString(setting, "value")
        }

    // Bool
    @Test
    fun setBool_savesValueToSharedPreferences() =
        runBlocking {
            val setting = Setting.TEST_BOOL
            sharedPreferencesManager.setBool(setting, true)
            val result = sharedPreferencesManager.getBool(setting)
            assertThat(result).isEqualTo(true)
        }

    @Test(expected = IllegalArgumentException::class)
    fun getBool_throwsIllegalArgumentException_whenSettingTypeIsNotBool(): Unit =
        runBlocking {
            val setting = Setting.TEST_STRING
            sharedPreferencesManager.setBool(setting, false)
        }

    @Test(expected = IllegalArgumentException::class)
    fun setBool_throwsIllegalArgumentException_whenSettingTypeIsNotBool(): Unit =
        runBlocking {
            val setting = Setting.TEST_STRING
            sharedPreferencesManager.setBool(setting, false)
        }

    // Int
    @Test
    fun setInt_savesValueToSharedPreferences() =
        runBlocking {
            val setting = Setting.TEST_INT
            sharedPreferencesManager.setInt(setting, 1)
            val result = sharedPreferencesManager.getInt(setting)
            assertThat(result).isEqualTo(1)
        }

    @Test(expected = IllegalArgumentException::class)
    fun getInt_throwsIllegalArgumentException_whenSettingTypeIsNotInt(): Unit =
        runBlocking {
            val setting = Setting.TEST_STRING
            sharedPreferencesManager.setInt(setting, 1)
        }

    @Test(expected = IllegalArgumentException::class)
    fun setInt_throwsIllegalArgumentException_whenSettingTypeIsNotInt(): Unit =
        runBlocking {
            val setting = Setting.TEST_STRING
            sharedPreferencesManager.setInt(setting, 1)
        }

    // Long
    @Test
    fun setLong_savesValueToSharedPreferences() =
        runBlocking {
            val setting = Setting.TEST_LONG
            sharedPreferencesManager.setLong(setting, 1L)
            val result = sharedPreferencesManager.getLong(setting)
            assertThat(result).isEqualTo(1L)
        }

    @Test(expected = IllegalArgumentException::class)
    fun getLong_throwsIllegalArgumentException_whenSettingTypeIsNotLong(): Unit =
        runBlocking {
            val setting = Setting.TEST_STRING
            sharedPreferencesManager.setLong(setting, 1L)
        }

    @Test(expected = IllegalArgumentException::class)
    fun setLong_throwsIllegalArgumentException_whenSettingTypeIsNotLong(): Unit =
        runBlocking {
            val setting = Setting.TEST_STRING
            sharedPreferencesManager.setLong(setting, 1L)
        }
}
