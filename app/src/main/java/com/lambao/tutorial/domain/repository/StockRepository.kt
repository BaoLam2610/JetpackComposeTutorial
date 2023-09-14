package com.lambao.tutorial.domain.repository

import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.domain.model.CompanyInfo
import com.lambao.tutorial.domain.model.CompanyListing
import com.lambao.tutorial.domain.model.IntradayInfo
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ) : Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String,
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}