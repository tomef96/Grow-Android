package com.example.grow.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userID: Int,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "waist") val waist: Int
)