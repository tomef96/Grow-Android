package com.example.grow

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.grow.ui.exercise.ExerciseFragment
import com.example.grow.ui.workout.WorkoutFragment
import java.lang.Exception


class WeekFragment : Fragment() {

    companion object {
        fun newInstance() = WeekFragment()
    }

    private lateinit var viewModel: WeekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.week_fragment, container, false)
        val btn = view.findViewById<Button>(R.id.start_workout_btn)
        btn.setOnClickListener {
            val activity = activity as MainActivity
            activity.setFragment(WorkoutFragment.newInstance())
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(WeekViewModel::class.java)
        }?: throw Exception("Invalid activity")
        // TODO: Use the ViewModel

    }

}
