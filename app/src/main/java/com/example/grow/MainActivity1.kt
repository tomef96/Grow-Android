package com.example.grow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grow.ui.exercise.ExerciseListAdapter

/*class MainActivity1 : AppCompatActivity() {

    private lateinit var exerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_workout)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ExerciseListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
        exerciseViewModel.allExercises.observe(this, Observer { exercises ->
            // Update the cached copy of the words in the adapter.
            exercises?.let { adapter.setExercises(it) }
        })
    }

}*/
