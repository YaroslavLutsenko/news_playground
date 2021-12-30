package com.lutsenko.news.presentation.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lutsenko.news.R
import com.lutsenko.news.presentation.models.ArticleItem
import kotlinx.android.synthetic.main.item_article.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var articles: List<ArticleItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = holder.bind(articles[position])

    override fun getItemCount(): Int = articles.size

    fun updateAllArticles(articles: List<ArticleItem>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    class NewsViewHolder constructor(view: View): RecyclerView.ViewHolder(view){
        fun bind(article: ArticleItem) = with(itemView) {
            val author = "${context.getString(R.string.author)}: ${article.author}"
            tvAuthor.text = author
            tvTitle.text = article.title
            tvDescription.text = article.description
        }
    }
}