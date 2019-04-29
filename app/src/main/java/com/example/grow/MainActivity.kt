package com.example.grow

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.grow.ui.nutrition.NutritionFragment
import com.example.grow.ui.stats.StatsFragment
import com.example.grow.ui.week.WeekFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), SensorEventListener {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.navigation_workout -> {
                setFragment(WeekFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_nutrition -> {
                setFragment(NutritionFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_stats -> {
                val fragment = StatsFragment.newInstance()
                fragment.arguments = bundleOf(Pair("steps", steps))
                setFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    /**
     * Step sensor: https://medium.com/@ssaurel/create-a-step-counter-fitness-app-for-android-with-kotlin-bbfb6ffe3ea7
     */
    var running = false
    var sensorManager: SensorManager? = null
    var steps: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.selectedItemId = R.id.navigation_workout
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeekFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        running = true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepsSensor == null) {
            
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (running) {
            steps = event.values[0]
        }
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun setFragment(fragment: Fragment, workoutID: Int) {
        val bundle = Bundle()
        bundle.putInt("workoutID", workoutID)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}

