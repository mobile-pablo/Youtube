package com.mobile.pablo.storage.tests

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.storage.database.typeconverter.ListConverter
import org.junit.Test

class ListConverterTest {

    @Test
    fun `fromList converts list of strings to single string`() {
        val list = listOf("one", "two", "three")
        val expected = "one,two,three"

        val result = ListConverter.fromList(list)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `toList converts single string to list of strings`() {
        val rawList = "one,two,three"
        val expected = listOf("one", "two", "three")

        val result = ListConverter.toList(rawList)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `fromList handles empty list`() {
        val list = emptyList<String>()
        val expected = ""

        val result = ListConverter.fromList(list)

        assertThat(result).isEqualTo(expected)
    }
}