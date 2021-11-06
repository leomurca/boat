package com.leomurca.boat.data.repository.datasource

import com.leomurca.boat.data.model.adapters.FeedAdapter
import com.leomurca.boat.data.network.FeedApi
import retrofit2.Response

class FeedRemoteDataSourceImpl(
    private val feedApi: FeedApi
) : FeedDataSource {
    override suspend fun feedWithURL(url: String): Response<FeedAdapter> {
        return feedApi.feed(url)
    }
}