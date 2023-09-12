package com.lambao.tutorial.data.repository

import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.data.csv.CSVParser
import com.lambao.tutorial.data.local.StockDatabase
import com.lambao.tutorial.data.mappers.toCompanyListing
import com.lambao.tutorial.data.remote.StockApi
import com.lambao.tutorial.domain.model.CompanyListing
import com.lambao.tutorial.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>
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
            }

        }
    }
}