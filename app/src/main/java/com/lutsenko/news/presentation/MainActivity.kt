package com.lutsenko.news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lutsenko.news.R
import com.lutsenko.news.presentation.news.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, NewsFragment::class.java, null)
                .commit()
        }
    }
}