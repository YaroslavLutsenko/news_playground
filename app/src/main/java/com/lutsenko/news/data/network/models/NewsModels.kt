package com.lutsenko.news.data.network.models

data class Article(val author: String?, val title: String, val description: String)

data class Headline(val status: String, val totalResults: Int, val articles: List<Article>)
