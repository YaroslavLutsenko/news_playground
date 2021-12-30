package com.lutsenko.news.di.components

import android.content.Context
import com.lutsenko.news.di.modules.DatabaseModule
import com.lutsenko.news.di.modules.NetworkModule
import com.lutsenko.news.di.modules.ViewModelModule
import com.lutsenko.news.presentation.MainActivity
import com.lutsenko.news.presentation.news.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, ViewModelModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun inject(fragment: NewsFragment)
}