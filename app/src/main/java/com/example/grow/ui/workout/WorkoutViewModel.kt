package com.example.grow.ui.workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.grow.AppDatabase
import com.example.grow.Exercise
import com.example.grow.ExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExerciseRepository
    val allExercises: LiveData<List<Exercise>>
    var selected: Exercise? = null

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    init {
        val exerciseDao = AppDatabase.getInstance(application, scope).exerciseDao()
        repository = ExerciseRepository(exerciseDao)
        allExercises = repository.allExercises
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(exercise: Exercise) = scope.launch(Dispatchers.IO) {
        repository.insert(exercise)
    }
}
