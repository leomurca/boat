package com.leomurca.boat.data.repository.datasource

import com.leomurca.boat.data.adapter.Channel
import com.leomurca.boat.data.adapter.Feed
import retrofit2.Response


class FeedInMemoryDataSource : FeedDataSource {
    override suspend fun feedWithURL(url: String): Response<Feed> {
        return Response.success(
            Feed(
                channel = Channel(
                    title = "Nat King Cole RSS Feed Titleeeeeeeee",
                    description = "There was a boy. A very enchanted boy. They say he wandered very far.",
                    language = "en-US"
                )
            )
        )
    }
}

