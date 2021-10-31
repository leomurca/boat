package com.leomurca.boat.data.repository.datasource

import com.leomurca.boat.data.model.Feed

class FeedInMemoryDataSource : FeedDataSource {
    override suspend fun feedWithURL(url: String): Feed {
        return when (url) {
            "nat" -> Feed(
                title = "Nat King Cole RSS Feed Titleeeeeeeee",
                description = "There was a boy. A very enchanted boy. They say he wandered very far.",
                language = "en-US",
                imagePath = "nat.jpg"
            )
            else -> Feed(title = "Default Title", description = "Default Description")
        }
    }
}