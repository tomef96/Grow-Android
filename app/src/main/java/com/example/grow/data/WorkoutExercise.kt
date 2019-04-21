package com.example.grow.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["workoutID", "exerciseID"])
data class WorkoutExercise(
    val workoutID: Int,
    val exerciseID: Int
)