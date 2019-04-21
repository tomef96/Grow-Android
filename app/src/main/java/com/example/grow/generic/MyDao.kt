package com.example.grow.generic

import androidx.room.*

interface MyDao<T> {
    @Insert
    fun insert(obj: T)

    @Delete
    fun delete(obj: T)

    @Update
    fun update(obj: T)
}