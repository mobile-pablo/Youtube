package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class PageInfoResponse(
    @Json(name = "totalResults") val totalResults: Int? = null,
    @Json(name = "resultsPerPage") val resultsPerPage: Int? = null
)