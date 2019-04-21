package com.example.grow.data.workout

import androidx.room.Dao
import androidx.room.Query
import com.example.grow.generic.MyDao

@Dao
interface WorkoutDao : MyDao<Workout> {

    @Query("SELECT * FROM workout")
    fun getAll(): List<Workout>

    @Query("SELECT * FROM workout WHERE id LIKE :id")
    fun findByID(id: Int): Workout

    @Query("DELETE FROM workout")
    fun deleteAll()
}