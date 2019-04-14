package com.example.grow.ui.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.grow.AppDatabase
import com.example.grow.Exercise
import com.example.grow.ExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    val repository: ExerciseRepository
    //val exercise: LiveData<Exercise>

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val exerciseDao = AppDatabase.getInstance(application, scope).exerciseDao()
        repository = ExerciseRepository(exerciseDao)
        //exercise = repository.allExercises()[0]
    }
}
