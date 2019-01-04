package com.example.movie.onetracktest.data.source

import com.example.movie.onetracktest.data.dataclasses.StepsInfo
import com.example.movie.onetracktest.data.source.local.StepsGoalChangedCallback
import com.example.movie.onetracktest.data.source.local.StepsGoalLocalSource
import com.example.movie.onetracktest.data.source.remote.ApiService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class StepsInfoRepository(
    private val remoteService: ApiService,
    private val local: StepsGoalLocalSource
) : StepsInfoSource {

    private val stepsGoalSubject = BehaviorSubject.create<Int>().apply {
        onNext(local.getStepsGoal())
    }

    init {
        local.registerStepsGoalChangedCallback(object : StepsGoalChangedCallback {
            override fun onStepsGoalChanged(newValue: Int) {
                stepsGoalSubject.onNext(newValue)
            }
        })
    }

    override fun getStepsInfo(): Single<List<StepsInfo>> = remoteService.getStepsInfo().subscribeOn(Schedulers.io())

    override fun setStepsGoal(goal: Int): Completable {
        return  Completable.fromCallable { local.setStepsGoal(goal) }
    }

    override fun getStepsGoal(): Observable<Int> {
        return stepsGoalSubject
    }

    companion object {
        private var INSTANCE: StepsInfoRepository? = null

        fun getInstance(remoteService: ApiService, local: StepsGoalLocalSource): StepsInfoRepository{
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = StepsInfoRepository(remoteService, local)
                    }
                }
            }

            return INSTANCE!!
        }
    }
}