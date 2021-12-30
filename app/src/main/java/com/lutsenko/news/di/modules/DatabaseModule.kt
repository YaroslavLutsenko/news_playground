package com.lutsenko.news.di.modules

import android.content.Context
import androidx.room.Room
import com.lutsenko.news.data.database.NewsDatabase
import com.lutsenko.news.data.database.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): NewsDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "News.db"
        ).build()

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase) : NewsDao = db.newsDao()

}