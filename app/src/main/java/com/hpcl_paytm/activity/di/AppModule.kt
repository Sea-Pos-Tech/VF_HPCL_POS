package com.hpcl_paytm.activity.di

import com.hpcl_paytm.activity.apicall.Api
import com.hpcl_paytm.activity.apicall.Repository
import com.hpcl_paytm.activity.apicall.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val APP_BASE_URL = ""

    @Provides
    @Singleton
    fun provideApiInstance(): Api {
        return Retrofit.Builder()
            .baseUrl(APP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(api: Api): Repository {
        return RepositoryImpl(api)
    }
}