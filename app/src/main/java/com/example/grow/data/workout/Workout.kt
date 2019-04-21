package com.example.grow.data.workout

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.grow.data.exercise.Exercise

@Entity
data class Workout(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "muscle_groups") val muscleGroups: String
) {
    @Ignore
    var exercises = arrayListOf<Exercise>()

}