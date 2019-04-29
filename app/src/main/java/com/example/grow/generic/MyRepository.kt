package com.example.grow.generic

import androidx.annotation.WorkerThread

abstract class MyRepository<T>(private val dao: MyDao<T>) {

    @WorkerThread
    fun insert(obj: T) {
        dao.insert(obj)
    }

    @WorkerThread
    fun delete(obj: T) {
        dao.delete(obj)
    }

}