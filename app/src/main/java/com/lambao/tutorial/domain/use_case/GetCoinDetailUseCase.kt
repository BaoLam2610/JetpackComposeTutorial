package com.lambao.tutorial.domain.use_case

import com.lambao.tutorial.data.mappers.toCoinDetail
import com.lambao.tutorial.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) : BaseUseCase() {
    operator fun invoke(id: String) = handleApi {
        repository.getCoinDetail(id).toCoinDetail()
    }
}