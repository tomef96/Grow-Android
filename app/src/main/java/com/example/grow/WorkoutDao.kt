package com.example.grow

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout")
    fun getAll(): List<Workout>

    @Query("SELECT * FROM workout WHERE id LIKE :id")
    fun findById(id: Int): Workout

    @Insert
    fun insert(workout: Workout)

    @Delete
    fun delete(workout: Workout)
}