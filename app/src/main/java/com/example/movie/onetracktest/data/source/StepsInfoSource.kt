package com.example.movie.onetracktest.data.source

import com.example.movie.onetracktest.data.dataclasses.StepsInfo
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface StepsInfoSource {
    fun getStepsInfo() : Single<List<StepsInfo>>

    fun setStepsGoal(goal: Int) : Completable

    fun getStepsGoal() : Observable<Int>
}