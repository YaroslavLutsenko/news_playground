package com.lutsenko.news.di.modules

import android.util.Log
import com.lutsenko.news.data.network.repository.NewsRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor { Log.d("RETROFIT 2", it) }.setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(Interceptor {
                val request: Request = it.request().newBuilder().addHeader("x-api-key", "9b058821e07a43dd9797f6350a0e3e48").build()
                return@Interceptor it.proceed(request)
            })
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsRepository = retrofit.create(NewsRepository::class.java)

}