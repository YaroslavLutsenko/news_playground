package com.lutsenko.news.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeadlineDb(
    @PrimaryKey val id: Int,
    val timestamp: Long,
    val json: String
)