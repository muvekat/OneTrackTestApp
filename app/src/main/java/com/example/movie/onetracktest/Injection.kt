package com.example.movie.onetracktest

import android.content.Context
import com.example.movie.onetracktest.data.source.StepsInfoRepository
import com.example.movie.onetracktest.data.source.local.StepsGoalLocalSource
import com.example.movie.onetracktest.data.source.remote.StepsInfoRemoteSource

object Injection{
    fun provideStepInfoRepository(context: Context): StepsInfoRepository{
        val local = StepsGoalLocalSource.getInstance(context)
        val remote = StepsInfoRemoteSource.getService()
        return StepsInfoRepository.getInstance(remote, local)
    }
}