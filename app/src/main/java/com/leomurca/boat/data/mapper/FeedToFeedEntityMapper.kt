package com.leomurca.boat.data.mapper

import com.leomurca.boat.data.database.entities.FeedEntity
import com.leomurca.boat.domain.model.Feed

class FeedToFeedEntityMapper : Mapper<Feed, FeedEntity> {
    override fun map(from: Feed): FeedEntity {
        return FeedEntity(
            title = from.title,
            description = from.description,
            imagePath = from.imagePath,
            language = from.language,
            url = from.url
        )
    }
}