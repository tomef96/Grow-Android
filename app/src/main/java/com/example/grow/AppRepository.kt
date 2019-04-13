package com.example.grow

import android.arch.lifecycle.LiveData
import androidx.annotation.WorkerThread

class AppRepository(appDatabase: AppDatabase) {

    private val exerciseDao = appDatabase.exerciseDao()

    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAll()

    @WorkerThread
    suspend fun insert(exercise: Exercise) {
        exerciseDao.insert(exercise)
    }
}