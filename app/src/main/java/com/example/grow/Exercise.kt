package com.example.grow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sets") var sets: Int,
    @ColumnInfo(name = "reps") var reps: Int
)