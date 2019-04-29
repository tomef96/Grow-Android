package com.example.grow.data.user

import androidx.lifecycle.LiveData
import com.example.grow.generic.MyRepository

class UserRepository(dao: UserDao) : MyRepository<User>(dao) {

    val user: LiveData<User> = dao.getUser()
}