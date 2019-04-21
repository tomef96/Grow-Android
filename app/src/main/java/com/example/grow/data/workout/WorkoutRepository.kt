package com.example.grow.data.workout

import androidx.annotation.WorkerThread
import com.example.grow.generic.MyRepository

class WorkoutRepository(private val dao: WorkoutDao) : MyRepository<Workout>(dao) {

    @WorkerThread
    suspend fun findByID(id: Int): Workout {
        return dao.findByID(id)
    }
}