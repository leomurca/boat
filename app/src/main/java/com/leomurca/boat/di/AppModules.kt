package com.leomurca.boat.di

import com.leomurca.boat.data.repository.FeedRepository
import com.leomurca.boat.data.repository.FeedRepositoryImpl
import com.leomurca.boat.data.repository.datasource.FeedDataSource
import com.leomurca.boat.data.repository.datasource.FeedInMemoryDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FeedDataSourceModule {

    @Singleton
    @Provides
    fun provideFeedDataSource(): FeedDataSource {
        return FeedInMemoryDataSource()
    }
}


@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class FeedRepositoryModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindFeedRepository(feedRepositoryImpl: FeedRepositoryImpl): FeedRepository
}