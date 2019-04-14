package com.example.grow.ui.exercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grow.Exercise
import com.example.grow.R

// https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#10
class ExerciseListAdapter internal constructor(
    private val context: Context, val setSelected: (Exercise) -> Boolean
) : RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<Exercise>()

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.exerciseName)
        val sets: TextView = itemView.findViewById(R.id.exerciseSets)
        val reps: TextView = itemView.findViewById(R.id.exerciseReps)
        val btn1: TextView = itemView.findViewById(R.id.rc_item_ex_btn1)
        val btn2: TextView = itemView.findViewById(R.id.rc_item_ex_btn2)
        val btn3: TextView = itemView.findViewById(R.id.rc_item_ex_btn3)
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
        holder.name.setOnClickListener {
            setSelected(current)
        }
        val buttons = arrayOf(holder.btn1, holder.btn2, holder.btn3)
        for (btn in buttons) {
            btn.setOnClickListener {
                when {
                    btn.text == "" -> btn.text = current.reps.toString()
                    btn.text.toString().toInt() != 0 -> btn.text = (btn.text.toString().toInt() - 1).toString()
                    else -> btn.text = current.reps.toString()
                }
            }
        }
    }

    internal fun setExercises(exercises: List<Exercise>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount() = exercises.size
}