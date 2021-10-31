package com.leomurca.boat.data.repository.datasource

import com.leomurca.boat.data.model.Feed

interface FeedDataSource {
    suspend fun feedWithURL(url: String): Feed
}