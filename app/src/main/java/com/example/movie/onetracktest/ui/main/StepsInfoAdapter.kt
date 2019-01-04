package com.example.movie.onetracktest.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.movie.onetracktest.R
import com.example.movie.onetracktest.data.dataclasses.StepsInfo
import java.text.SimpleDateFormat
import java.util.*

class StepsInfoHolder constructor(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN)

    @BindView(R.id.dateTextView)
    lateinit var dateTextView: TextView
    @BindView(R.id.TotalStepsTextView)
    lateinit var totalStepsTextView: TextView
    @BindView(R.id.StepsWalkTextView)
    lateinit var stepsWalkTextView: TextView
    @BindView(R.id.StepsAerobicTextView)
    lateinit var stepsAerobicTextView: TextView
    @BindView(R.id.StepsRunTextView)
    lateinit var stepsRunTextView: TextView
    @BindView(R.id.GoalReachedView)
    lateinit var goalReachedView: View
    @BindView(R.id.threeSectionProgressBar)
    lateinit var threeSectionProgressBar: ThreeSectionProgressBar

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bind(stepsInfo: StepsInfo, goal: Int) {
        val dateFormatted = dateFormatter.format(stepsInfo.date)
        dateTextView.text = dateFormatted

        val totalSteps = stepsInfo.aerobic + stepsInfo.run + stepsInfo.walk
        val goalFormatted =
            totalSteps.toString() + " / " + goal.toString() + " " + context.getString(R.string.steps_walked)
        totalStepsTextView.text = goalFormatted

        stepsWalkTextView.text = stepsInfo.walk.toString()
        threeSectionProgressBar.firstProgress = stepsInfo.walk

        stepsAerobicTextView.text = stepsInfo.aerobic.toString()
        threeSectionProgressBar.secondProgress = stepsInfo.aerobic

        stepsRunTextView.text = stepsInfo.run.toString()
        threeSectionProgressBar.thirdProgress = stepsInfo.run

        if (totalSteps >= goal){
            goalReachedView.visibility = View.VISIBLE
        } else {
            goalReachedView.visibility = View.GONE
        }
    }

}

class StepsInfoAdapter: RecyclerView.Adapter<StepsInfoHolder>(){

    private val listOfStepsInfo = mutableListOf<StepsInfo>()
    private var goal:Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsInfoHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.steps_info_item, parent, false)
        return StepsInfoHolder(view, parent.context)
    }

    override fun getItemCount() = listOfStepsInfo.size

    override fun onBindViewHolder(holder: StepsInfoHolder, position: Int) {
        holder.bind(listOfStepsInfo[position], goal)
    }

    fun setData(list: List<StepsInfo>){
        listOfStepsInfo.clear()
        listOfStepsInfo.addAll(list)
        notifyDataSetChanged()
    }

    fun setGoal(newGoal: Int){
        goal = newGoal
        notifyDataSetChanged()
    }
}