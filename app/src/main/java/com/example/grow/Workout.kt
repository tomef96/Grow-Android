package com.example.grow

class Workout(private val exercises: ArrayList<Exercise>) {

    fun getExercise(id: Int): Exercise {
        return exercises.filter { exercise -> exercise.id == id }[0]
    }
}