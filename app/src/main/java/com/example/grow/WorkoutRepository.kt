package com.example.grow

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WorkoutRepository(private val dao: WorkoutDao) : MyRepository<Workout>(dao) {

    @WorkerThread
    suspend fun findByID(id: Int): Workout {
        return dao.findByID(id)
    }
}