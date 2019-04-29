package com.example.grow

import com.example.grow.data.exercise.Exercise
import com.example.grow.data.workout.Workout

/**
 * Act as the users personal trainer, asking questions and analyzing workout, adjusting on the go
 */
class PersonalTrainer {

    companion object {
        val instance = PersonalTrainer()
    }

    /**
     * Analyze the workout, getting an overview over results and trends
     */
    fun analyzeWorkout(workout: Workout) {
        // Total weight lifted
        // Time used
        // Weight per minute
        // Weight gain/loss
    }

    /**
     * Adjust the workout, reducing og increasing the workload
     */
    fun adjustWorkout(workout: Workout): Workout {
        return workout
    }

    /**
     * Adjust the exercise, reducing or increasing the workload
     */
    fun adjustExercise(exercise: Exercise): Exercise {
        if (exercise.isSuccess()) {
            when {
                exercise.reps < 12 -> exercise.reps += 1
                exercise.sets < 5 -> exercise.sets += 1
                else -> {
                    exercise.weight += 2.5
                    exercise.sets = 3
                    exercise.reps = 6
                }
            }
        }
        return exercise
    }

}