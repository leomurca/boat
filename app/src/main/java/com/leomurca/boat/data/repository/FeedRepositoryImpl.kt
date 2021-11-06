package com.leomurca.boat.data.repository

import com.leomurca.boat.data.adapter.Feed
import com.leomurca.boat.data.repository.datasource.FeedDataSource
import retrofit2.Response
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val dataSource: FeedDataSource
) : FeedRepository {
    override suspend fun feedWithURL(url: String): Response<Feed> {
        return dataSource.feedWithURL(url)
    }
}
