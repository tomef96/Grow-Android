package com.example.grow

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    // https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#6
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "grow-db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}