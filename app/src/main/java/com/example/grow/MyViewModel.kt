package com.example.grow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class MyViewModel(application: Application) : AndroidViewModel(application) {

    var parentJob = Job()

    val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    val scope = CoroutineScope(coroutineContext)

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}