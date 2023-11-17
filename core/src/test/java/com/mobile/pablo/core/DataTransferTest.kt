package com.mobile.pablo.core

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.core.data.DataTransfer
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DataTransferTest {

    companion object {

        private const val FOO_TEXT = "test"
        private const val FOO_ERROR = "test error"
    }

    @Test
    fun mapTransformsDataWhenSuccessful() = runTest {
        val dataTransfer = DataTransfer(FOO_TEXT)
        val result = dataTransfer.map { it.length }

        assertThat(result.isSuccessful).isTrue()
        assertThat(result.data).isEqualTo(FOO_TEXT.length)
        assertThat(result.error).isNull()
    }

    @Test
    fun mapPreservesErrorWhenNotSuccessful() = runTest {
        val exception = Exception(FOO_ERROR)
        val dataTransfer = DataTransfer<String>(error = exception)
        val result = dataTransfer.map { it.length }

        assertThat(result.isSuccessful).isFalse()
        assertThat(result.error).isEqualTo(exception)
        assertThat(result.data).isNull()
    }

    @Test
    fun doIfSuccessfulExecutesActionWhenSuccessful() {
        var actionExecuted = false
        val dataTransfer = DataTransfer(FOO_TEXT)

        dataTransfer.doIfSuccessful { actionExecuted = true }

        assertThat(actionExecuted).isTrue()
    }

    @Test
    fun doIfSuccessfulDoesNotExecuteActionWhenNotSuccessful() {
        var actionExecuted = false
        val dataTransfer = DataTransfer<String>(error = Exception(FOO_ERROR))

        dataTransfer.doIfSuccessful { actionExecuted = true }

        assertThat(actionExecuted).isFalse()
    }
}