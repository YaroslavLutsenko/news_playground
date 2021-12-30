package com.lutsenko.news.data.network.repository

import com.lutsenko.news.data.network.models.Headline
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRepository {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String = "us"): Flowable<Headline>

}