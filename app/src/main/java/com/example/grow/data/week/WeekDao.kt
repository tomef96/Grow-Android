package com.example.grow.data.week

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeekDao {

    @Query("SELECT * FROM week")
    fun getAll(): List<Week>

    @Query("SELECT * FROM week WHERE id LIKE :id")
    fun findById(id: Int): Week

    @Insert
    fun insert(week: Week)

    @Delete
    fun delete(week: Week)
}