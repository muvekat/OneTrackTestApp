package com.example.movie.onetracktest.ui.main

import com.example.movie.onetracktest.data.source.StepsInfoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(private val stepsInfoRepository: StepsInfoRepository, private val mainView: MainContract.View) :
    MainContract.Presenter {

    private val mCompositeDisposable = CompositeDisposable()

    init {
        mainView.presenter = this
    }

    override fun start() {
        loadTasks()

        mCompositeDisposable.add(
            stepsInfoRepository.getStepsGoal()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { target -> mainView.showStepsTarget(target) }
        )
    }

    override fun loadTasks() {
        mainView.setLoadingIndicator(true)

        mCompositeDisposable.add(
            stepsInfoRepository.getStepsInfo().observeOn(AndroidSchedulers.mainThread())
                .subscribe { stepsList, error ->
                    mainView.setLoadingIndicator(false)
                    if (stepsList != null) {
                        mainView.showSteps(stepsList)
                    }
                    if (error != null) {
                        mainView.showLoadingStepsError()
                    }
                }
        )
    }

    override fun setStepsTarget(target: Int) {
        mCompositeDisposable.add(
            stepsInfoRepository.setStepsGoal(target)
                .subscribe()
        )
    }

    override fun stop() {
        mCompositeDisposable.clear()
    }
}