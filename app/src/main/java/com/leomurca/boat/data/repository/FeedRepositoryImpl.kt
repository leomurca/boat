package com.leomurca.boat.data.repository

import com.leomurca.boat.data.model.Feed
import com.leomurca.boat.data.repository.datasource.FeedDataSource
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val dataSource: FeedDataSource
) : FeedRepository {
    override suspend fun feedWithURL(url: String): Feed {
        return dataSource.feedWithURL(url)
    }
}
