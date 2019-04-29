package com.example.grow.ui.workout

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.grow.data.AppDatabase
import com.example.grow.data.workoutExercise.WorkoutExerciseRepository
import com.example.grow.data.exercise.Exercise
import com.example.grow.generic.MyViewModel

class WorkoutExerciseViewModel(application: Application) : MyViewModel(application) {
    private val dao = AppDatabase.getInstance(application, scope).workoutExerciseDao()
    private val repo = WorkoutExerciseRepository.getInstance(dao)

    private var tempId: Int? = null
    var workoutID: Int = 0
        set(value) {
            println("$value | $tempId")
            if (value != tempId) {
                tempId = value
                exercises = repo.findByID(value)
            }
        }

    var exercises: LiveData<List<Exercise>> = repo.findByID(workoutID)


    val selected: MutableLiveData<Exercise> = MutableLiveData()

    init {

    }


}
