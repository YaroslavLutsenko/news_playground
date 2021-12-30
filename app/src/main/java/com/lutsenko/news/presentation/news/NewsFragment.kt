package com.lutsenko.news.presentation.news

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lutsenko.news.NewsApplication
import com.lutsenko.news.R
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment : Fragment(R.layout.fragment_news) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var newsViewModel: NewsViewModel

    private val adapter = NewsAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as NewsApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsViewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        newsViewModel.getData().observe(this) {
            if (it.isSuccess) {
                adapter.updateAllArticles(it.getOrNull() ?: emptyList())
            } else {
                // todo: add handle error
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.adapter = adapter
    }

}