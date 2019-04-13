package com.example.grow

import android.arch.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getAll(): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE id LIKE :id")
    fun findByID(id: Int): LiveData<Exercise>

    @Insert
    fun insert(exercise: Exercise)

    @Delete
    fun delete(exercise: Exercise)
}