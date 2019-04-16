package com.example.grow

import android.app.Application

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
