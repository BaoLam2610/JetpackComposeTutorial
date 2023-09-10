package com.lambao.tutorial.domain.use_case

import com.lambao.tutorial.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) : BaseUseCase() {
    operator fun invoke() = handleApi {
        repository.getCoins()
    }.flowOn(Dispatchers.IO)
}