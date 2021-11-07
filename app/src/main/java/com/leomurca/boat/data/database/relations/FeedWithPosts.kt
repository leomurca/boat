package com.leomurca.boat.data.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.leomurca.boat.data.database.entities.FeedEntity
import com.leomurca.boat.data.database.entities.PostEntity

data class FeedWithPosts(
    @Embedded
    val feed: FeedEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "feedId"
    )
    val posts: List<PostEntity>
)