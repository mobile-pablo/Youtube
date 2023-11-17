package com.mobile.pablo.core

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.core.data.callSafe
import java.net.HttpURLConnection
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

class SafeResponseHandlerTest {

    companion object {

        private const val FOO_TEXT = "test"
        private const val FOO_ERROR = "test error"
    }

    @Test
    fun callSafeReturnsSuccessfulResponse() = runTest {
        val response = callSafe { Response.success(FOO_TEXT) }

        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isEqualTo(FOO_TEXT)
    }

    @Test(expected = RuntimeException::class)
    fun callSafeReturnsErrorResponse() = runTest {
        val response = callSafe<String> { throw Exception(FOO_ERROR) }

        assertThat(response.isSuccessful).isFalse()
        assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_UNSUPPORTED_TYPE)
        assertThat(response.message()).isEqualTo(FOO_ERROR)
    }
}