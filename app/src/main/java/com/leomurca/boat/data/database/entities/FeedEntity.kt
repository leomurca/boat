package com.leomurca.boat.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feeds")
data class FeedEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long? = 0L,

    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "description")
    var description: String? = "",

    @ColumnInfo(name = "imagePath")
    var imagePath: String? = "",

    @ColumnInfo(name = "language")
    var language: String? = "",

    @ColumnInfo(name = "url")
    var url: String? = ""
)