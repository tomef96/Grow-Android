package com.example.grow

class Workout(private val exercises: ArrayList<Exercise>) {

    fun getExercise(id: Int): Exercise? {
        return exercises.firstOrNull { exercise -> exercise.id == id }
    }
}