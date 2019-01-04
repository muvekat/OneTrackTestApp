package com.example.movie.onetracktest

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.movie.onetracktest", appContext.packageName)
    }

    @Test
    fun testRepo() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val repo = Injection.provideStepInfoRepository(appContext)
        val disp = repo.getStepsGoal().subscribe { println(it) }
        repo.setStepsGoal(10).subscribe()
        Thread.sleep(150)

        repo.getStepsInfo().subscribe { t -> println(t) }
        Thread.sleep(1000)
    }
}
