package com.example.grow.data.workoutExercise

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.grow.data.exercise.Exercise
import com.example.grow.generic.MyDao

@Dao
interface WorkoutExerciseDao : MyDao<WorkoutExercise> {

    @Query(
        "SELECT exercise.id, name, sets, reps FROM workoutexercise JOIN workout ON workout.id = workoutexercise.workoutID JOIN exercise ON exercise.id = workoutexercise.exerciseID WHERE workoutID = :workoutID")
    //@Query("SELECT * FROM exercise WHERE id = :workoutID")
    fun getWorkoutExercises(workoutID: Int): LiveData<List<Exercise>>

    @Query("SELECT exercise.id FROM workoutexercise JOIN workout ON workout.id = workoutexercise.workoutID JOIN exercise ON exercise.id = workoutexercise.exerciseID WHERE workoutID = :workoutID")
    fun getExercises(workoutID: Int): LiveData<List<Int>>

    @Query("SELECT exercise.id, name, sets, reps FROM workoutexercise JOIN workout ON workout.id = workoutexercise.workoutID JOIN exercise ON exercise.id = workoutexercise.exerciseID")
    fun getAll(): LiveData<List<Exercise>>

    @Query("DELETE FROM workoutexercise")
    fun deleteAll()

}