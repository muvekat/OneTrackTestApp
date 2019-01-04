package com.example.movie.onetracktest.ui.main

import com.example.movie.onetracktest.data.dataclasses.StepsInfo

interface MainContract{

    interface View{
        var presenter: Presenter

        fun setLoadingIndicator(active: Boolean)

        fun showLoadingStepsError()

        fun showSteps(tasks: List<StepsInfo>)

        fun showStepsTarget(target: Int)
    }

    interface Presenter{
        fun start()

        fun loadTasks()

        fun setStepsTarget(target: Int)

        fun stop()
    }

}