package com.lambao.tutorial.data.remote.services

import com.lambao.tutorial.data.remote.dto.CoinDetailDto
import com.lambao.tutorial.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{id}")
    suspend fun getCoin(
        @Path("id") id: String
    ): CoinDetailDto
}