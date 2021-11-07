package com.leomurca.boat.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "description")
    var description: String? = "",

    @ColumnInfo(name = "link")
    var link: String? = "",

    @ColumnInfo(name = "pubDate")
    var pubDate: String? = "",

    @ColumnInfo(name = "feedId")
    var feedId: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L
}