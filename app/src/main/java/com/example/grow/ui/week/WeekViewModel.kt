package com.example.grow.ui.week

import android.app.Application
import com.example.grow.data.AppDatabase
import com.example.grow.generic.MyRepository
import com.example.grow.generic.MyViewModel
import com.example.grow.data.week.WeekRepository

class WeekViewModel(application: Application) : MyViewModel(application) {

    val repository: MyRepository<WeekRepository>
        get() = TODO("not implemented")

    //val workouts: List<Workout>

    init {
        val workoutDao = AppDatabase.getInstance(application, scope).workoutDao()
        //repository = WorkoutRepository(workoutDao)
        //workouts = repository.allWorkouts
    }
}
