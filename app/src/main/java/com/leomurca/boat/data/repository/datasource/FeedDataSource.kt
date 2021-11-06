package com.leomurca.boat.data.repository.datasource

import com.leomurca.boat.data.model.adapters.FeedAdapter
import retrofit2.Response

interface FeedDataSource {
    suspend fun feedWithURL(url: String): Response<FeedAdapter>
}