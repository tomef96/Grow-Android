package com.example.grow.ui.stats

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.grow.data.AppDatabase
import com.example.grow.data.user.User
import com.example.grow.data.user.UserRepository
import com.example.grow.generic.MyViewModel

class StatsViewModel(application: Application) : MyViewModel(application) {

    val dao = AppDatabase.getInstance(application, scope).userDao()
    private val repo = UserRepository(dao)
    val user: LiveData<User> = repo.user


    /*var userHeight = user.value?.height.toString()
    var userWeight = user.value?.weight.toString()
    var userWaist = user.value?.waist.toString()*/
}
