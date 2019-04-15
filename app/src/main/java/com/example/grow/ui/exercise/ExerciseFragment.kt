package com.example.grow.ui.exercise

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.grow.Exercise

import com.example.grow.R
import com.example.grow.ui.workout.WorkoutViewModel


class ExerciseFragment : Fragment() {

    lateinit var exercise: Exercise

    // UI
    lateinit var name: TextView
    lateinit var sets: TextView
    lateinit var reps: TextView

    private lateinit var viewModel: WorkoutViewModel

    companion object {
        fun newInstance() = ExerciseFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.exercise_fragment, container, false)
        populate(view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(WorkoutViewModel::class.java)
        }?: throw Exception("Invalid Activity")
        exercise = viewModel.selected!!

        activity?.setTitle(R.string.toolbar_title_exercise)
    }

    private fun populate(view: View) {
        name = view.findViewById(R.id.detailedExerciseName)
        name.text = exercise.name
        sets = view.findViewById(R.id.detailedExerciseSets)
        sets.text = context?.getString(R.string.exercise_sets, exercise.sets)
        reps = view.findViewById(R.id.detailedExerciseReps)
        reps.text = context?.getString(R.string.exercise_reps, exercise.reps)
    }

}
