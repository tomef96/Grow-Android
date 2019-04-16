package com.example.grow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Week(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "workouts") val workouts: List<Workout>
)