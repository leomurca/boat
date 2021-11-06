package com.leomurca.boat.data.mapper

import com.leomurca.boat.data.model.adapters.FeedAdapter
import com.leomurca.boat.domain.model.Feed
import com.leomurca.boat.domain.model.Post

class FeedAdapterToFeedMapper : Mapper<FeedAdapter, Feed> {
    override fun map(from: FeedAdapter): Feed {
        return Feed(
            title = from.channel?.title ?: "Feed without Title",
            description = from.channel?.description,
            imagePath = from.channel?.image?.url,
            language = from.channel?.language,
            items = from.channel?.items?.map { item ->
                Post(
                    title = item.title,
                    description = item.description,
                    link = item.link,
                    pubDate = item.pubDate
                )
            }
        )
    }
}