package com.example.grow.ui.workout

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grow.Exercise
import com.example.grow.MainActivity
import com.example.grow.ui.exercise.ExerciseListAdapter
import com.example.grow.R
import com.example.grow.ui.exercise.ExerciseFragment

class WorkoutFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance() = WorkoutFragment()
    }

    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.workout_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(WorkoutViewModel::class.java)
        }?: throw Exception("Invalid Activity")
        recyclerView = activity!!.findViewById(R.id.recyclerview)
        val adapter = ExerciseListAdapter(context!!, this::setSelected)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)

        viewModel.allExercises.observe(this, Observer { exercises ->
            exercises?.let { adapter.setExercises(it) }
        })
        activity?.setTitle(R.string.toolbar_title_workout)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val listState = recyclerView.layoutManager?.onSaveInstanceState()
        outState.putParcelable("list_state", listState)
    }

    private fun setSelected(exercise: Exercise): Boolean {
        viewModel.selected = exercise
        val activity = activity as MainActivity
        activity.setFragment(ExerciseFragment.newInstance())
        return true
    }

    interface OnWorkoutSelectedListener {
        fun onExerciseSelected(position: Int)
    }

}
