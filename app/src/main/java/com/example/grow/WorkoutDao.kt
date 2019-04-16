package com.example.grow

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDao : MyDao<Workout> {

    @Query("SELECT * FROM workout")
    fun getAll(): List<Workout>

    @Query("SELECT * FROM workout WHERE id LIKE :id")
    fun findByID(id: Int): Workout

    @Query("DELETE FROM workout")
    fun deleteAll()
}