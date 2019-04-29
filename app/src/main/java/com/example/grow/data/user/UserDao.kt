package com.example.grow.data.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.grow.generic.MyDao

@Dao
interface UserDao : MyDao<User> {

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>

    @Query("DELETE FROM user")
    fun deleteAll()

}