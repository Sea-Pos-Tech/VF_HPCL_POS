package com.hpcl_paytm.activity.di

import android.content.Context
import androidx.room.Room
import com.hpcl_paytm.activity.apicall.APIService
import com.hpcl_paytm.activity.apicall.AuthInterceptor
import com.hpcl_paytm.activity.apicall.Repository
import com.hpcl_paytm.activity.apicall.RepositoryImpl
import com.hpcl_paytm.activity.apicall.Utils
import com.hpcl_paytm.activity.room.AppRoomDatabase
import com.hpcl_paytm.activity.room.dao.BatchTransactionDao
import com.hpcl_paytm.activity.room.dao.OperatorsDao
import com.hpcl_paytm.activity.room.dao.RegistrationDao
import com.hpcl_paytm.activity.room.dao.SchemaDao
import com.hpcl_paytm.activity.room.dao.SettlementReportDao
import com.hpcl_paytm.activity.room.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiInstance(): APIService {
        return Retrofit.Builder()
            .baseUrl(Utils.MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor())
                    .build()
            )
            .build()
            .create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppRoomDatabase =
        Room.databaseBuilder(context, AppRoomDatabase::class.java, "HPCLDatabase")
            .build()

    @Provides
    fun providesBatchTransactionDao(appRoomDatabase: AppRoomDatabase): BatchTransactionDao =
        appRoomDatabase.batchTransactionDao()

    @Provides
    fun providesOperatorsDao(appRoomDatabase: AppRoomDatabase): OperatorsDao =
        appRoomDatabase.operatorsDao()

    @Provides
    fun providesRegistrationDao(appRoomDatabase: AppRoomDatabase): RegistrationDao =
        appRoomDatabase.registrationDao()

    @Provides
    fun providesSettlementReportDao(appRoomDatabase: AppRoomDatabase): SettlementReportDao =
        appRoomDatabase.settlementReportDao()

    @Provides
    fun providesSchemaDao(appRoomDatabase: AppRoomDatabase): SchemaDao =
        appRoomDatabase.schemaDao()

    @Provides
    fun providesTransactionDao(appRoomDatabase: AppRoomDatabase): TransactionDao =
        appRoomDatabase.transactionDao()

    @Provides
    @Singleton
    fun provideApiRepository(api: APIService): Repository {
        return RepositoryImpl(api)
    }
}