package com.leomurca.boat.data.repository

import com.leomurca.boat.data.model.Feed

interface FeedRepository {
    suspend fun feedWithURL(url: String): Feed?
}