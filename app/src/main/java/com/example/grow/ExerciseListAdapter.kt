package com.example.grow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#10
class ExerciseListAdapter internal constructor(
    private val context: Context
) : RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<Exercise>()

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.exerciseName)
        val sets: TextView = itemView.findViewById(R.id.exerciseSets)
        val reps: TextView = itemView.findViewById(R.id.exerciseReps)
        init {
            itemView.setOnClickListener {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_exercise, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val current = exercises[position]
        holder.name.text = context.getString(R.string.exercise_name, current.name)
        holder.sets.text = context.getString(R.string.exercise_sets, current.sets)
        holder.reps.text = context.getString(R.string.exercise_reps, current. reps)
    }

    internal fun setExercises(exercises: List<Exercise>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount() = exercises.size
}