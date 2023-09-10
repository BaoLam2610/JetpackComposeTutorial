package com.lambao.tutorial.data.repository

import com.lambao.tutorial.data.mappers.toCoin
import com.lambao.tutorial.data.mappers.toCoinDetail
import com.lambao.tutorial.data.remote.services.CoinApi
import com.lambao.tutorial.domain.model.Coin
import com.lambao.tutorial.domain.model.CoinDetail
import com.lambao.tutorial.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: CoinApi
) : CoinRepository {
    override suspend fun getCoins(): List<Coin> {
        return apiService.getCoins().map { it.toCoin() }
    }

    override suspend fun getCoinDetail(id: String): CoinDetail {
        return apiService.getCoin(id).toCoinDetail()
    }
}