package com.leomurca.boat.data.mapper

import com.leomurca.boat.data.database.entities.FeedEntity
import com.leomurca.boat.domain.model.Feed

class FeedEntityToFeedMapper : Mapper<FeedEntity, Feed> {
    override fun map(from: FeedEntity): Feed {
        return Feed(
            title = from.title ?: "",
            description = from.description,
            imagePath = from.imagePath,
            url = from.url ?: ""
        )
    }
}