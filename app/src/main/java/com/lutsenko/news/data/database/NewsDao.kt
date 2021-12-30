package com.lutsenko.news.data.database

import androidx.room.*
import io.reactivex.rxjava3.core.Single


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(headlineDb: HeadlineDb)

    @Update
    fun update(headlineDb: HeadlineDb)

    @Query("SELECT * FROM headlinedb WHERE id = :id")
    fun getHeadlineDb(id: Int = 1): Single<HeadlineDb>

}