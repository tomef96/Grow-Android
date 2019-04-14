package com.example.grow.ui.workout

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grow.ExerciseListAdapter
import com.example.grow.R

class WorkoutFragment : Fragment() {

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
        viewModel = ViewModelProviders.of(this).get(WorkoutViewModel::class.java)
        // TODO: Use the ViewModel
        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ExerciseListAdapter(context!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)

        viewModel.allExercises.observe(this, Observer { exercises ->
            // Update the cached copy of the words in the adapter.
            exercises?.let { adapter.setExercises(it) }
        })
    }

}
