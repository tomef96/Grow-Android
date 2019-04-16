package com.example.grow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "muscle_groups") val muscleGroups: String,
    @ColumnInfo(name = "exerciseID") val exerciseID: Int
)