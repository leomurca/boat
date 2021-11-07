package com.leomurca.boat.data.database.daos

import androidx.room.*
import com.leomurca.boat.data.database.entities.FeedEntity
import com.leomurca.boat.data.database.entities.PostEntity
import com.leomurca.boat.data.database.relations.FeedWithPosts

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(feed: FeedEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM feeds")
    fun getFeeds(): List<FeedEntity>

    @Transaction
    @Query("SELECT * FROM feeds WHERE id = :feedId")
    suspend fun getFeedWithPosts(feedId: Long): FeedWithPosts
}
