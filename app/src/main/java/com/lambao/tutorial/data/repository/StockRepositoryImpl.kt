package com.lambao.tutorial.data.repository

import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.data.csv.CSVParser
import com.lambao.tutorial.data.local.StockDatabase
import com.lambao.tutorial.data.mappers.toCompanyInfo
import com.lambao.tutorial.data.mappers.toCompanyListing
import com.lambao.tutorial.data.mappers.toCompanyListingEntity
import com.lambao.tutorial.data.remote.StockApi
import com.lambao.tutorial.domain.model.CompanyInfo
import com.lambao.tutorial.domain.model.CompanyListing
import com.lambao.tutorial.domain.model.IntradayInfo
import com.lambao.tutorial.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>,
) : StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(data = localListings.map { it.toCompanyListing() }))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
                companyListingParser.parse(response.byteStream())
            } catch (e: Exception) {
                emit(Resource.Error("ERROR ERROR ERROR"))
                null
            }

            remoteListings?.let { listings ->
                emit(Resource.Loading(false))
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    dao.searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val result = intradayInfoParser.parse(response.byteStream())
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val response = api.getCompanyInfo(symbol)
            Resource.Success(response.toCompanyInfo())
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}