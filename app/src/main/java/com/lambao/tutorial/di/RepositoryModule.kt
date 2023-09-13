package com.lambao.tutorial.di

import com.lambao.tutorial.data.csv.CSVParser
import com.lambao.tutorial.data.csv.CompanyListingsParser
import com.lambao.tutorial.data.repository.StockRepositoryImpl
import com.lambao.tutorial.domain.model.CompanyListing
import com.lambao.tutorial.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}