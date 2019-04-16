package com.example.grow

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ExerciseRepository(private val exerciseDao: ExerciseDao) : MyRepository<Exercise>(exerciseDao) {

    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAll()

    @WorkerThread
    suspend fun findByID(id: Int): LiveData<Exercise> {
        return exerciseDao.findByID(id)
    }
}