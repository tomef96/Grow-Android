package com.example.grow.ui.workout

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grow.data.exercise.Exercise
import com.example.grow.MainActivity
import com.example.grow.ui.exercise.ExerciseListAdapter
import com.example.grow.R
import com.example.grow.ui.exercise.ExerciseFragment

class WorkoutFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: WorkoutExerciseViewModel

    companion object {
        fun newInstance() = WorkoutFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val workoutID = arguments?.getInt("workoutID")
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(WorkoutExerciseViewModel::class.java)
        }?: throw Exception("Invalid Activity")
        viewModel.workoutID = workoutID!!
        activity?.setTitle(R.string.toolbar_title_workout)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.workout_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = activity!!.findViewById(R.id.recyclerview)
        val adapter = ExerciseListAdapter(context!!, this::setSelected)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        viewModel.exercises.observe(this, Observer { exercises ->
            exercises?.let { adapter.setExercises(it) }
        })
    }

    private fun setSelected(exercise: Exercise): Boolean {
        viewModel.selected.value = exercise
        val activity = activity as MainActivity
        activity.setFragment(ExerciseFragment.newInstance())
        return true
    }

    interface OnWorkoutSelectedListener {
        fun onExerciseSelected(position: Int)
        fun setSelected(id: Int)
    }

}
