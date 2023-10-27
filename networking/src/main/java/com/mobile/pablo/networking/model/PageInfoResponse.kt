package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class PageInfoResponse(
    @Json(name = "totalResults") var totalResults: Int? = null,
    @Json(name = "resultsPerPage") var resultsPerPage: Int? = null
)