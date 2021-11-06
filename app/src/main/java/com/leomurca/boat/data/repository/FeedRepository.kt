package com.leomurca.boat.data.repository

import com.leomurca.boat.data.adapter.Feed
import retrofit2.Response

interface FeedRepository {
    suspend fun feedWithURL(url: String): Response<Feed>
}