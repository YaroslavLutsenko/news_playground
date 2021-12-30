package com.lutsenko.news.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.lutsenko.news.domain.NewsService
import com.lutsenko.news.presentation.mapper.ArticleMapper
import javax.inject.Inject
import androidx.lifecycle.Transformations
import com.lutsenko.news.presentation.models.ArticleItem


class NewsViewModel @Inject constructor(
    private var service: NewsService,
    private val mapper: ArticleMapper
    ): ViewModel() {

    fun getData(): LiveData<Result<List<ArticleItem>>> =
        Transformations.map(LiveDataReactiveStreams.fromPublisher(service.getHeadlines())) { mapper.map(it) }

}