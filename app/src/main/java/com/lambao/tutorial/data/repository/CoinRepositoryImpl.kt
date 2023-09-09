package com.lambao.tutorial.data.repository

import com.lambao.tutorial.data.remote.dto.CoinDetailDto
import com.lambao.tutorial.data.remote.dto.CoinDto
import com.lambao.tutorial.data.remote.services.CoinApi
import com.lambao.tutorial.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val apiService: CoinApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return apiService.getCoins()
    }

    override suspend fun getCoinDetail(id: String): CoinDetailDto {
        return apiService.getCoin(id)
    }
}