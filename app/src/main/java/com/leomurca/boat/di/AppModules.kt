package com.leomurca.boat.di

import android.content.Context
import androidx.room.Room
import com.leomurca.boat.data.database.AppDatabase
import com.leomurca.boat.data.database.daos.FeedDao
import com.leomurca.boat.data.network.FeedApi
import com.leomurca.boat.data.repository.FeedRepository
import com.leomurca.boat.data.repository.FeedRepositoryImpl
import com.leomurca.boat.data.repository.datasource.FeedDataSource
import com.leomurca.boat.data.repository.datasource.FeedRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.buildDatabase(appContext)
    }

    @Provides
    fun provideFeedDao(appDatabase: AppDatabase): FeedDao {
        return appDatabase.feedDao()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummybaseurl")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideFeedApi(retrofit: Retrofit): FeedApi {
        return retrofit.create(FeedApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFeedDataSource(feedApi: FeedApi): FeedDataSource {
        return FeedRemoteDataSourceImpl(feedApi)
    }

    @Singleton
    @Provides
    fun provideFeedRepository(
        remoteFeedDataSource: FeedDataSource,
        feedDao: FeedDao
    ): FeedRepository {
        return FeedRepositoryImpl(remoteFeedDataSource, feedDao)
    }
}