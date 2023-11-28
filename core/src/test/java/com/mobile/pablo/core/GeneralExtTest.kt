package com.mobile.pablo.core

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.core.data.DataTransfer
import com.mobile.pablo.core.ext.map
import java.net.HttpURLConnection
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class GeneralExtTest {
    companion object {
        private const val FOO_TEXT = "test"
        private const val TEXT_PLAIN = "text/plain"
    }

    @Test
    fun mapTransformsSuccessfulResponse() =
        runTest {
            val response = Response.success(FOO_TEXT)
            val result = response.map { it.length }

            assertThat(result.data).isEqualTo(DataTransfer(FOO_TEXT.length).data)
            assertThat(result.isSuccessful).isTrue()
        }

    @Test
    fun mapTransformsErrorResponse() =
        runTest {
            val response: Response<String> =
                Response.error(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    FOO_TEXT
                        .toResponseBody(TEXT_PLAIN.toMediaTypeOrNull())
                )
            val result = response.map { it.length }
            val wantedResult = DataTransfer<String>(error = HttpException(response))
            assertThat(result.error!!.message).isEqualTo(wantedResult.error!!.message)
        }
}
