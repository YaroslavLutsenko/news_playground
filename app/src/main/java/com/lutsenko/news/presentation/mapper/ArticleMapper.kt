package com.lutsenko.news.presentation.mapper

import com.lutsenko.news.data.network.models.Headline
import com.lutsenko.news.presentation.models.ArticleItem
import java.lang.NullPointerException
import javax.inject.Inject

interface Mapper<T, V> {
    fun map(it: T): V
}

class ArticleMapper @Inject constructor(): Mapper<Result<Headline>, Result<List<ArticleItem>>> {
    override fun map(it: Result<Headline>): Result<List<ArticleItem>> {
        return if (it.isSuccess) {
            val articles = it.getOrNull()?.articles ?: emptyList()
            val items = articles.map { article -> ArticleItem(article.author ?: "?", article.title, article.description) }
            Result.success(items)
        } else {
            Result.failure(it.exceptionOrNull() ?: NullPointerException("Result's exception is null"))
        }
    }
}