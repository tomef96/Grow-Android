package com.example.grow.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.grow.data.exercise.Exercise
import com.example.grow.data.exercise.ExerciseDao
import com.example.grow.generic.MyRepository

class WorkoutExerciseRepository(private val dao: WorkoutExerciseDao) : MyRepository<WorkoutExercise>(dao) {

    val allExercises: LiveData<List<Exercise>> = dao.getAll()

    fun findByID(id: Int): LiveData<List<Exercise>> = dao.getWorkoutExercises(id)



    //https://github.com/googlesamples/android-sunflower/blob/master/app/src/main/java/com/google/samples/apps/sunflower/data/PlantRepository.kt
    companion object {
        @Volatile private var instance: WorkoutExerciseRepository? = null

        fun getInstance(dao: WorkoutExerciseDao) =
                instance ?: synchronized(this) {
                    instance ?: WorkoutExerciseRepository(dao).also { instance = it }
                }
    }
}