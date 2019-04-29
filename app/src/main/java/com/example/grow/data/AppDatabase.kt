package com.example.grow.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.grow.data.user.User
import com.example.grow.data.user.UserDao
import com.example.grow.data.exercise.Exercise
import com.example.grow.data.exercise.ExerciseDao
import com.example.grow.data.workout.Workout
import com.example.grow.data.workout.WorkoutDao
import com.example.grow.data.workoutExercise.WorkoutExercise
import com.example.grow.data.workoutExercise.WorkoutExerciseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class, Workout::class, WorkoutExercise::class, User::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutExerciseDao(): WorkoutExerciseDao
    abstract fun userDao(): UserDao

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
                ).fallbackToDestructiveMigration().addCallback(
                    ExerciseDatabaseCallback(
                        scope
                    )
                ).build()
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
                    populateDatabase(database.workoutDao())
                    populateDatabase(database.workoutExerciseDao())
                    populateDatabase(database.userDao())
                }
            }
        }

        fun populateDatabase(exerciseDao: ExerciseDao) {
            exerciseDao.deleteAll()

            var exercise = Exercise(420, "Barbell Bench Press", 3, 12)
            exerciseDao.insert(exercise)
            exercise = Exercise(421, "Dips", 4, 15)
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

        fun populateDatabase(workoutDao: WorkoutDao) {
            workoutDao.deleteAll()

            var workout = Workout(420, "Monday", "Chest")
            workoutDao.insert(workout)
            workout = Workout(69, "Tuesday", "Back")
            workoutDao.insert(workout)
        }

        fun populateDatabase(workoutExerciseDao: WorkoutExerciseDao) {
            workoutExerciseDao.deleteAll()

            var workoutExercise = WorkoutExercise(420, 420)
            workoutExerciseDao.insert(workoutExercise)
            workoutExercise = WorkoutExercise(420, 421)
            workoutExerciseDao.insert(workoutExercise)
            workoutExercise = WorkoutExercise(69, 69)
            workoutExerciseDao.insert(workoutExercise)
        }

        fun populateDatabase(userDao: UserDao) {
            userDao.deleteAll()

            val user = User(1, 178, 75, 82)
            userDao.insert(user)
        }
    }


}