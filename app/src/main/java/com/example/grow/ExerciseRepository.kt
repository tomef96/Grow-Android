package com.example.grow

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAll()

    @WorkerThread
    suspend fun insert(exercise: Exercise) {
        exerciseDao.insert(exercise)
    }
}