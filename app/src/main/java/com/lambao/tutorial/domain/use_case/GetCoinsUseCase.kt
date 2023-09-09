package com.lambao.tutorial.domain.use_case

import com.lambao.tutorial.data.mappers.toCoin
import com.lambao.tutorial.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) : BaseUseCase() {
    operator fun invoke() = handleApi {
        repository.getCoins().map { it.toCoin() }
    }
}