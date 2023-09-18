package com.mobile.pablo.networking.interceptor

import com.mobile.pablo.core.util.EMPTY_STRING
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RequestInterceptor : Interceptor {

    companion object {
        private const val USER_AGENT = "User-Agent"
        private const val ANDROID = "Android"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return try {
            val requestBuilder = request.newBuilder()
                .addHeader(USER_AGENT, ANDROID)
            chain.proceed(requestBuilder.build())
        } catch (timeout: SocketTimeoutException) {
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_2)
                .code(HttpURLConnection.HTTP_CLIENT_TIMEOUT)
                .body(EMPTY_STRING.toResponseBody())
                .message("SocketTimeout ${timeout.message}")
                .build()
        } catch (unknown: UnknownHostException) {
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_2)
                .code(HttpURLConnection.HTTP_UNAVAILABLE)
                .body(EMPTY_STRING.toResponseBody())
                .message("Unknown  ${unknown.message}")
                .build()
        } catch (ex: Exception) {
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_2)
                .code(HttpURLConnection.HTTP_INTERNAL_ERROR)
                .body(EMPTY_STRING.toResponseBody())
                .message("Exception  ${ex.message}")
                .build()
        }
    }
}