package com.leomurca.boat.data.repository

import com.leomurca.boat.data.database.daos.FeedDao
import com.leomurca.boat.data.mapper.FeedAdapterToFeedMapper
import com.leomurca.boat.data.mapper.FeedToFeedEntityMapper
import com.leomurca.boat.data.model.ResultOf
import com.leomurca.boat.data.repository.datasource.FeedDataSource
import com.leomurca.boat.domain.model.Feed
import com.leomurca.boat.extension.toResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val dataSource: FeedDataSource,
    private val feedDao: FeedDao
) : FeedRepository {
    override suspend fun feedWithURL(url: String): ResultOf<Feed> {
        return dataSource
            .feedWithURL(url)
            .toResultOf(FeedAdapterToFeedMapper())
    }

    override suspend fun saveFeed(feed: Feed): ResultOf<Unit> {
        return withContext(Dispatchers.IO) {
            feedDao.insertFeed(FeedToFeedEntityMapper().map(feed))
            ResultOf.Success(Unit)
        }
    }
}
