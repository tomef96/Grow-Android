package com.example.grow.data.week

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.grow.data.workout.Workout

@Entity
data class Week(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "workouts") val workouts: List<Workout>
)