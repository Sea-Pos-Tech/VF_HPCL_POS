package com.hpcl_paytm.activity.di

import android.content.Context
import androidx.room.Room
import com.hpcl_paytm.activity.apicall.APIService
import com.hpcl_paytm.activity.apicall.AuthInterceptor
import com.hpcl_paytm.activity.apicall.Repository
import com.hpcl_paytm.activity.apicall.RepositoryImpl
import com.hpcl_paytm.activity.apicall.Utils
import com.hpcl_paytm.activity.room.AppRoomDatabase
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
        Room.databaseBuilder(context, AppRoomDatabase::class.java, "appRoomDatabase")
            .build()

    @Provides
    fun providesPostDao(appRoomDatabase: AppRoomDatabase): PostDao =
        appRoomDatabase.getPostDao()

    @Provides
    @Singleton
    fun provideApiRepository(api: APIService): Repository {
        return RepositoryImpl(api)
    }
}