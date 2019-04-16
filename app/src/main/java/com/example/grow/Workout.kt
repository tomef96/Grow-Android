package com.example.grow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "exercises") val exercises: List<Exercise>
)