package com.lambao.tutorial.domain.repository

import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.domain.model.CompanyListing
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ) : Flow<Resource<List<CompanyListing>>>
}