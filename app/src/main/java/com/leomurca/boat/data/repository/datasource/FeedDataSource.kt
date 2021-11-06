package com.leomurca.boat.data.repository.datasource

import com.leomurca.boat.data.adapter.Feed
import retrofit2.Response

interface FeedDataSource {
    suspend fun feedWithURL(url: String): Response<Feed>
}