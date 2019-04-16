package com.example.grow

import androidx.annotation.WorkerThread

abstract class MyRepository<T>(private val dao: MyDao<T>) {

    @WorkerThread
    suspend fun insert(obj: T) {
        dao.insert(obj)
    }

    @WorkerThread
    suspend fun delete(obj: T) {
        dao.delete(obj)
    }

}