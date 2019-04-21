package com.example.grow.ui.workout

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.grow.MainActivity
import com.example.grow.data.AppDatabase
import com.example.grow.data.WorkoutExerciseRepository
import com.example.grow.data.exercise.Exercise
import com.example.grow.data.exercise.ExerciseRepository
import com.example.grow.data.workout.Workout
import com.example.grow.data.workout.WorkoutRepository
import com.example.grow.generic.MyViewModel
import com.example.grow.ui.exercise.ExerciseListAdapter
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class WorkoutExerciseViewModel(application: Application) : MyViewModel(application) {
    val dao = AppDatabase.getInstance(application, scope).workoutExerciseDao()
    val repo = WorkoutExerciseRepository.getInstance(dao)
    var workoutID: Int = 0
    val allExercises = repo.allExercises

    val exercises: LiveData<List<Exercise>>?
        get() {
            val result = repo.findByID(workoutID)
            if (result != exercisesCopy) {
                exercisesCopy = result
                return repo.findByID(workoutID)
            }
            return null
        }

    var exercisesCopy: LiveData<List<Exercise>>? = null

    val selected: MutableLiveData<Exercise> = MutableLiveData()


    fun update() {

    }

    init {

    }
}

/*class WorkoutExerciseViewModel(application: Application) : MyViewModel(application) {

    private val workoutExerciseRepository: WorkoutExerciseRepository
    private val workoutRepository: WorkoutRepository
    private val exerciseRepository: ExerciseRepository

    /*private val exercises by lazy {
        MutableLiveData<List<Exercise>>().also {
            loadExercises()
            println(it.value)
        }
    }*/
    val workoutID: MutableLiveData<Int> = MutableLiveData()

    private val exercises: LiveData<List<Exercise>>

    //private val exercises = MutableLiveData<List<Exercise>>

    //var workout: Workout? = null
    var selected: Exercise? = null

    fun getExercises(): LiveData<List<Exercise>> {
        return exercises
    }

    private fun loadExercises() {
        //exercises.postValue(workoutExerciseRepository.findByID(workoutID.value!!).value)
    }

    init {
        val db = AppDatabase.getInstance(application, scope)
        val workoutDao = db.workoutDao()
        workoutRepository = WorkoutRepository(workoutDao)
        val workoutExerciseDao = db.workoutExerciseDao()
        workoutExerciseRepository = WorkoutExerciseRepository(workoutExerciseDao)
        val exerciseDao = db.exerciseDao()
        exerciseRepository = ExerciseRepository(exerciseDao)

        exercises = workoutExerciseRepository.findByID(workoutID.value!!)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    /*fun insert(exercise: Exercise) = scope.launch(Dispatchers.IO) {
        repository.insert(exercise)
    }*/

}*/
