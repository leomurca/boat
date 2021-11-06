package com.leomurca.boat.data.repository.datasource

import com.leomurca.boat.data.adapter.Feed
import com.leomurca.boat.data.network.FeedApi
import retrofit2.Response

class FeedRemoteDataSourceImpl(
    private val feedApi: FeedApi
) : FeedDataSource {
    override suspend fun feedWithURL(url: String): Response<Feed> {
        return feedApi.feed(url)
    }
}