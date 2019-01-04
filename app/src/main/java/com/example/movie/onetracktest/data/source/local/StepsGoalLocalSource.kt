package com.example.movie.onetracktest.data.source.local

import android.content.Context
import android.content.SharedPreferences
import com.example.movie.onetracktest.R

private const val STEPS_GOAL_KEY = "GOAL"

class StepsGoalLocalSource(context: Context){
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.SHARED_PREF_FILENAME),
        Context.MODE_PRIVATE
    )

    private var stepsGoalChangedListener: SharedPreferences.OnSharedPreferenceChangeListener? = null

    fun getStepsGoal() = sharedPreferences.getInt(STEPS_GOAL_KEY, 0)

    fun setStepsGoal(goal: Int) = sharedPreferences.edit().putInt(STEPS_GOAL_KEY, goal).commit()

    /**
     * This function is called to register listener which calls callback.
     */
    fun registerStepsGoalChangedCallback(callback: StepsGoalChangedCallback) {
        stepsGoalChangedListener =
                SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                    if (key == STEPS_GOAL_KEY){
                        val newValue = sharedPreferences.getInt(key, 0)
                        callback.onStepsGoalChanged(newValue)
                    }
                }
        sharedPreferences.registerOnSharedPreferenceChangeListener(stepsGoalChangedListener)
    }

    /**
     * This function should be called when we don't need our callback anymore.
     */
    fun unregisterStepsGoalChangedCallback() {
        if (stepsGoalChangedListener != null)
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(stepsGoalChangedListener)
    }

    companion object {
        private var INSTANCE: StepsGoalLocalSource? = null

        fun getInstance(context: Context): StepsGoalLocalSource{
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = StepsGoalLocalSource(context)
                    }
                }
            }

            return INSTANCE!!
        }
    }
}