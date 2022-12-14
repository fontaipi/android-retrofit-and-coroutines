package com.example.retrofitandcoroutine.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL: String = "https://api.themoviedb.org/3/"

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService :  TheMovieDbService by lazy{
        retrofit.create(TheMovieDbService::class.java)
    }
}