package com.mobile.pablo.networking.model.common

import com.squareup.moshi.Json

internal data class PageInfoResponse(
    @Json(name = "totalResults") val totalResults: Int? = null,
    @Json(name = "resultsPerPage") val resultsPerPage: Int? = null
)
