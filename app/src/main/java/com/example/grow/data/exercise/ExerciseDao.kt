package com.example.grow.data.exercise

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.grow.generic.MyDao

@Dao
interface ExerciseDao : MyDao<Exercise> {

    @Query("SELECT * FROM exercise")
    fun getAll(): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE id LIKE :id")
    fun findByID(id: Int): LiveData<Exercise>

    @Query("DELETE FROM exercise")
    fun deleteAll()

}