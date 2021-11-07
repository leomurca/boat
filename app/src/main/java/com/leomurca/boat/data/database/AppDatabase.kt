package com.leomurca.boat.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leomurca.boat.data.database.daos.FeedDao
import com.leomurca.boat.data.database.entities.FeedEntity
import com.leomurca.boat.data.database.entities.PostEntity

@Database(entities = [FeedEntity::class, PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun feedDao(): FeedDao

    companion object {
        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "app_database.db"
            ).build()
        }
    }
}