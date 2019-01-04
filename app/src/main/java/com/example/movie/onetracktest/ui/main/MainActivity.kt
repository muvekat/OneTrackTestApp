package com.example.movie.onetracktest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movie.onetracktest.Injection
import com.example.movie.onetracktest.R
import com.example.movie.onetracktest.data.dataclasses.StepsInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    override lateinit var presenter: MainContract.Presenter

    private lateinit var mStepsInfoAdapter: StepsInfoAdapter

    override fun setLoadingIndicator(active: Boolean) {
        //Do nothing for now
    }

    override fun showLoadingStepsError() {
        //Do nothing for now
    }

    override fun showSteps(tasks: List<StepsInfo>) {
        mStepsInfoAdapter.setData(tasks)
    }

    override fun showStepsTarget(target: Int) {
        mStepsInfoAdapter.setGoal(target)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStepsInfoAdapter = StepsInfoAdapter()
        stepsInfoRecyclerView.adapter = mStepsInfoAdapter

        targetButton.setOnClickListener {
            showSetTargetDialog(this, object : SetTargetDialogCallback {
                override fun call(newGoal: Int) {
                    presenter.setStepsTarget(newGoal)
                }
            })
        }

        presenter = MainPresenter(Injection.provideStepInfoRepository(applicationContext), this).apply {
            start()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}
