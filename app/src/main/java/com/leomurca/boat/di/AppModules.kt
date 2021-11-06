package com.leomurca.boat.di

import com.leomurca.boat.data.network.FeedApi
import com.leomurca.boat.data.repository.FeedRepository
import com.leomurca.boat.data.repository.FeedRepositoryImpl
import com.leomurca.boat.data.repository.datasource.FeedDataSource
import com.leomurca.boat.data.repository.datasource.FeedRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

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
    fun provideFeedRepository(remoteFeedDataSource: FeedDataSource): FeedRepository {
        return FeedRepositoryImpl(remoteFeedDataSource)
    }
}