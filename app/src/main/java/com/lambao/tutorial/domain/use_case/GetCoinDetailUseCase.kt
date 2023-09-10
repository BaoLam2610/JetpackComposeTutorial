package com.lambao.tutorial.domain.use_case

import com.lambao.tutorial.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) : BaseUseCase() {
    operator fun invoke(id: String) = handleApi {
        repository.getCoinDetail(id)
    }.flowOn(Dispatchers.IO)
}