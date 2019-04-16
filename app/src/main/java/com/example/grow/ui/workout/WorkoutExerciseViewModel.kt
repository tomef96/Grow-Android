package com.example.grow.ui.workout

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.grow.AppDatabase
import com.example.grow.Exercise
import com.example.grow.ExerciseRepository
import com.example.grow.MyViewModel
import com.example.grow.ui.exercise.ExerciseListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WorkoutExerciseViewModel(application: Application) : MyViewModel(application) {

    val repository: ExerciseRepository
    val allExercises: LiveData<List<Exercise>>
    var selected: Exercise? = null

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
