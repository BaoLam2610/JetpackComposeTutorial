package com.lambao.tutorial.di

import com.lambao.tutorial.common.Constants
import com.lambao.tutorial.data.remote.services.CoinApi
import com.lambao.tutorial.data.repository.CoinRepositoryImpl
import com.lambao.tutorial.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinApi() : CoinApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinApi) : CoinRepository {
        return CoinRepositoryImpl(api)
    }
}