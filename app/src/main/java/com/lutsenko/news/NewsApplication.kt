package com.lutsenko.news

import android.app.Application
import com.lutsenko.news.di.components.ApplicationComponent
import com.lutsenko.news.di.components.DaggerApplicationComponent

class NewsApplication: Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(applicationContext)
    }

}