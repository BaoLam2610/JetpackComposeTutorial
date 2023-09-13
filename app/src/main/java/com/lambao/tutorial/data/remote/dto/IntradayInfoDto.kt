package com.lambao.tutorial.data.remote.dto

import com.squareup.moshi.Json

data class IntradayInfoDto(
    @field:Json(name = "timestamp") val timestamp: String,
    @field:Json(name = "close") val close: Double,
)
