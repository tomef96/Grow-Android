package com.example.grow

import androidx.lifecycle.LiveData
import androidx.room.*

interface MyDao<T> {
    @Insert
    fun insert(obj: T)

    @Delete
    fun delete(obj: T)

    @Update
    fun update(obj: T)
}