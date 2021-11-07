package com.leomurca.boat.data.repository

import com.leomurca.boat.data.model.ResultOf
import com.leomurca.boat.domain.model.Feed

interface FeedRepository {
    suspend fun feedWithURL(url: String): ResultOf<Feed>
    suspend fun saveFeed(feed: Feed): ResultOf<Unit>
    suspend fun feeds(): ResultOf<List<Feed>>
}