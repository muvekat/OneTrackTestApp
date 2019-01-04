package com.example.movie.onetracktest.data.source.remote

import com.example.movie.onetracktest.utils.DateUnixTimestampTypeAdapter
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

private const val URL = "https://intern-f6251.firebaseio.com/"

object StepsInfoRemoteSource{
    private val gson = GsonBuilder().registerTypeAdapter(Date::class.java, DateUnixTimestampTypeAdapter()).create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val service = retrofit.create(ApiService::class.java)

    fun getService() = service
}