package com.example.grow

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ExerciseTest {
    private lateinit var exercise: Exercise

    @Before
    fun init() {
        exercise = Exercise(420, "Barbell Curl", 3, 12)
    }

    @Test
    fun createdCorrectly() {
        assertTrue(exercise.id == 420 && exercise.name == "Barbell Curl" && exercise.sets == 3 && exercise.reps == 12)
    }

    @Test
    fun setsAndRepsIsEditable() {
        exercise.sets = 4
        exercise.reps = 15
        assertTrue(exercise.sets == 4 && exercise.reps == 15)
    }


}