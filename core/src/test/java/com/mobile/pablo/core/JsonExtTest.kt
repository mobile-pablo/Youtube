package com.mobile.pablo.core

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.core.ext.fromJson
import com.mobile.pablo.core.ext.fromJsonList
import com.mobile.pablo.core.ext.toJson
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Test

class JsonExtensionsTest {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

    companion object {

        private const val JSON_ITEM_ONE_TITLE = "test"
        private const val JSON_ITEM_ONE_VALUE = 123
        private const val JSON_ITEM_TWO_TITLE = "test2"
        private const val JSON_ITEM_TWO_VALUE = 456
        private const val JSON_ITEM = "{\"name\":\"$JSON_ITEM_ONE_TITLE\",\"value\":$JSON_ITEM_ONE_VALUE}"

        private const val JSON_LIST = "[" +
                "{\"name\":\"$JSON_ITEM_ONE_TITLE\",\"value\":$JSON_ITEM_ONE_VALUE}," +
                "{\"name\":\"$JSON_ITEM_TWO_TITLE\",\"value\":$JSON_ITEM_TWO_VALUE}" +
                "]"
    }

    @Test
    fun toJsonConvertsObjectToJsonString() {
        val data = TestData(JSON_ITEM_ONE_TITLE, JSON_ITEM_ONE_VALUE)
        val json = data.toJson(moshi)

        assertThat(json).isEqualTo(JSON_ITEM)
    }

    @Test
    fun fromJsonConvertsJsonStringToObject() {
        val data = JSON_ITEM.fromJson<TestData>(moshi)

        assertThat(data).isEqualTo(TestData(JSON_ITEM_ONE_TITLE, JSON_ITEM_ONE_VALUE))
    }

    @Test
    fun fromJsonListConvertsJsonStringToListOfObjects() {
        val data = JSON_LIST.fromJsonList<TestData>(moshi)

        assertThat(data).isEqualTo(
            listOf(
                TestData(JSON_ITEM_ONE_TITLE, JSON_ITEM_ONE_VALUE),
                TestData(JSON_ITEM_TWO_TITLE, JSON_ITEM_TWO_VALUE)
            )
        )
    }

    @Test(expected = JsonEncodingException::class)
    fun fromJsonThrowsExceptionWhenCannotConvert() {
        val json = "invalid json"
        json.fromJson<TestData>(moshi)
    }
}

internal data class TestData(
    val name: String,
    val value: Int
)