package com.leomurca.boat.data.repository

import com.leomurca.boat.data.mapper.FeedAdapterToFeedMapper
import com.leomurca.boat.data.model.ResultOf
import com.leomurca.boat.data.repository.datasource.FeedDataSource
import com.leomurca.boat.domain.model.Feed
import com.leomurca.boat.extension.toResultOf
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val dataSource: FeedDataSource
) : FeedRepository {
    override suspend fun feedWithURL(url: String): ResultOf<Feed> {
        return dataSource
            .feedWithURL(url)
            .toResultOf(FeedAdapterToFeedMapper())
    }
}
