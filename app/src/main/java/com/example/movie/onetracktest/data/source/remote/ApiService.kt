package com.example.movie.onetracktest.data.source.remote

import com.example.movie.onetracktest.data.dataclasses.StepsInfo
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService{
    @GET("intern/metric.json")
    fun getStepsInfo(): Single<List<StepsInfo>>
}