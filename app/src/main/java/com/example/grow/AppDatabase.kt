package com.example.grow

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

    // https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#6
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "grow-db"
                ).fallbackToDestructiveMigration().addCallback(ExerciseDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class ExerciseDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.exerciseDao())
                }
            }
        }

        fun populateDatabase(exerciseDao: ExerciseDao) {
            exerciseDao.deleteAll()

            var exercise = Exercise(420, "Barbell Bench Press", 3, 12)
            exerciseDao.insert(exercise)
            exercise = Exercise(69, "Barbell Rows", 4, 8)
            exerciseDao.insert(exercise)
            exercise = Exercise(42069, "Pull Ups", 2, 15)
            exerciseDao.insert(exercise)
            exercise = Exercise(69420, "Hammer Curls", 5, 12)
            exerciseDao.insert(exercise)
            exercise = Exercise(1337, "Calf Raise", 5, 20)
            exerciseDao.insert(exercise)
        }
    }


}