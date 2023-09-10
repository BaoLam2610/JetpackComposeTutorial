package com.lambao.tutorial.domain.repository

import com.lambao.tutorial.domain.model.Coin
import com.lambao.tutorial.domain.model.CoinDetail

interface CoinRepository {
    suspend fun getCoins(): List<Coin>

    suspend fun getCoinDetail(id: String): CoinDetail
}