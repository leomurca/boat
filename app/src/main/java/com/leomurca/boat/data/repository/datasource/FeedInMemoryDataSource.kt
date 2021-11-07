package com.leomurca.boat.data.repository.datasource

import com.leomurca.boat.data.model.adapters.ChannelAdapter
import com.leomurca.boat.data.model.adapters.FeedAdapter
import retrofit2.Response

class FeedInMemoryDataSource : FeedDataSource {
    override suspend fun feedWithURL(url: String): Response<FeedAdapter> {
        return Response.success(
            FeedAdapter(
                channel = ChannelAdapter(
                    title = "Nat King Cole RSS Feed Titleeeeeeeee",
                    description = "There was a boy. A very enchanted boy. They say he wandered very far.",
                    language = "en-US",
                )
            )
        )
    }
}

