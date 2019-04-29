package com.example.grow

import com.example.grow.data.exercise.Exercise
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test

class PersonalTrainerTest {

    private val pt = PersonalTrainer.instance

    @Test
    fun adjustSets() {
        val exercise = createTestExercise(12, 3, 12)
        assertThat(exercise.isSuccess(), `is`(true))
        pt.adjustExercise(exercise)
        assertThat(exercise.sets, `is`(4))
        assertThat(exercise.reps, `is`(12))
    }

    @Test
    fun adjustReps() {
        val exercise = createTestExercise(10, 3, 10)
        pt.adjustExercise(exercise)
        assertThat(exercise.sets, `is`(3))
        assertThat(exercise.reps, `is`(11))
    }

    @Test
    fun adjustWeights() {
        val exercise = createTestExercise(12, 5, 12)
        pt.adjustExercise(exercise)
        assertThat(exercise.sets, `is`(3))
        assertThat(exercise.reps, `is`(6))
        assertThat(exercise.weight, `is`(22.5))
    }

    @Test
    fun doesNotAdjust() {
        val exercise = createTestExercise(10, 3, 9)
        pt.adjustExercise(exercise)
        assertThat(exercise.sets, `is`(3))
        assertThat(exercise.reps, `is`(10))
    }

    private fun createTestExercise(reps: Int, sets: Int, result: Int): Exercise {
        val exercise = Exercise(1, "Sample", sets, reps)
        val results = arrayOf(result, result, result)
        var i = 1
        results.forEach { exercise.results[i] = it; i++ }
        exercise.weight = 20.0
        return exercise
    }
}