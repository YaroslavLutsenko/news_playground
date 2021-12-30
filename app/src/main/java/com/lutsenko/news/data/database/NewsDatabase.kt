package com.lutsenko.news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HeadlineDb::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}