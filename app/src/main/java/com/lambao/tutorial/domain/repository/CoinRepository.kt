package com.lambao.tutorial.domain.repository

import com.lambao.tutorial.data.remote.dto.CoinDetailDto
import com.lambao.tutorial.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinDetail(id: String): CoinDetailDto
}