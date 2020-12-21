package com.example.studentmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.studentmanager.R
import androidx.navigation.findNavController

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonStudents = view.findViewById<View>(R.id.buttonStudents)
        val buttonCourses = view.findViewById<View>(R.id.buttonCourses)
        val buttonReport = view.findViewById<View>(R.id.buttonReport)

        buttonStudents.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_studentListFragment)
        }

        buttonCourses.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_coursesListFragment)
        }

        buttonReport.setOnClickListener {
            Toast.makeText(context, "Report", Toast.LENGTH_SHORT).show()
        }
    }
}