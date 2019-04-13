package com.example.grow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getInstance(applicationContext)
    }
}
