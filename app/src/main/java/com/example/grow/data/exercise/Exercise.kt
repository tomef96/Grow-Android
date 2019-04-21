package com.example.grow.data.exercise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sets") var sets: Int,
    @ColumnInfo(name = "reps") var reps: Int
) {

    @Ignore
    var results = mutableMapOf<String, Int>()
    @Ignore
    var weight: Double = 0.0

    fun isSuccess(): Boolean {
        if (!results.isNullOrEmpty()) {
            var result = false
            for (r in results) {
                if (r.value == sets) result = true
            }
            return result
        }
        return false
    }
}
