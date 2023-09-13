package com.lambao.tutorial.domain.use_case

import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.domain.model.CompanyListing
import com.lambao.tutorial.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class GetCompanyListingsUseCase @Inject constructor(
//    private val repository: StockRepository
//) {
//    suspend operator fun invoke(
//        fetchFromRemote: Boolean,
//        query: String
//    ): Flow<Resource<List<CompanyListing>>> =
//        repository.getCompanyListings(fetchFromRemote, query)
//}