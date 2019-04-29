package com.example.grow.ui.exercise

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.grow.data.exercise.Exercise

import com.example.grow.R
import com.example.grow.ui.workout.WorkoutExerciseViewModel


class ExerciseFragment : Fragment() {

    lateinit var exercise: Exercise

    // UI
    lateinit var name: TextView
    lateinit var sets: TextView
    lateinit var reps: TextView
    lateinit var image: ImageView

    private val viewModel: WorkoutExerciseViewModel by lazy {
        ViewModelProviders.of(this.activity!!).get(WorkoutExerciseViewModel::class.java)
    }

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
        /*viewModel = activity?.run {
            ViewModelProviders.of(this).get(WorkoutExerciseViewModel::class.java)
        }?: throw Exception("Invalid Activity")*/
        exercise = viewModel.selected.value!!

        activity?.setTitle(R.string.toolbar_title_exercise)
    }

    private fun populate(view: View) {
        name = view.findViewById(R.id.detailedExerciseName)
        name.text = exercise.name
        sets = view.findViewById(R.id.detailedExerciseSets)
        sets.text = context?.getString(R.string.exercise_sets, exercise.sets)
        reps = view.findViewById(R.id.detailedExerciseReps)
        reps.text = context?.getString(R.string.exercise_reps, exercise.reps)
        image = view.findViewById(R.id.detailedExerciseImage)

        val allImages = mutableMapOf<String, Int>()
        allImages["barbell bench press"] = R.drawable.barbell_bench_press
        allImages["dips"] = R.drawable.dips
        allImages["barbell rows"] = R.drawable.barbell_row
        image.setImageResource(allImages[exercise.name.toLowerCase()] ?: 0)
    }

}
