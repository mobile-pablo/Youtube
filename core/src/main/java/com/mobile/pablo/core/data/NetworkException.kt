package com.mobile.pablo.core.data

data class NetworkException(
    val code: Int,
    override val message: String
) : Exception()
