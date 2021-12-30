package com.lutsenko.news.domain

import android.util.Log
import com.google.gson.Gson
import com.lutsenko.news.data.database.HeadlineDb
import com.lutsenko.news.data.database.NewsDao
import com.lutsenko.news.data.network.models.Headline
import com.lutsenko.news.data.network.repository.NewsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class NewsService @Inject constructor(
    private val remoteRepository: NewsRepository,
    private val localRepository: NewsDao
) {
    private val tag = NewsService::class.simpleName
    private val gson = Gson()

    fun getHeadlines(): Flowable<Result<Headline>>  {
        return fetchHeadlinesFromDatabase()
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext { fetchHeadlinesFromNetwork() }
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun fetchHeadlinesFromDatabase(): Flowable<Result<Headline>> {
        Log.d(tag, "fetchHeadlinesFromDatabase")
        return localRepository.getHeadlineDb()
            .subscribeOn(Schedulers.io())
            .map {
                val expirationPeriod = TimeUnit.DAYS.toMillis(1)
                if (System.currentTimeMillis() - it.timestamp > expirationPeriod)
                    throw TimeoutException("Timestamp is expired")

                Result.success(gson.fromJson(it.json, Headline::class.java))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
    }

    private fun fetchHeadlinesFromNetwork(): Flowable<Result<Headline>> {
        Log.d(tag, "fetchHeadlinesFromNetwork")
        return remoteRepository.getTopHeadlines()
            .subscribeOn(Schedulers.io())
            .map { Result.success(it) }
            .onErrorReturn { Result.failure(it) }
            .doOnNext { result ->
                result.getOrNull()?.let {
                    localRepository.insert(HeadlineDb(1, System.currentTimeMillis(), gson.toJson(it)))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

}