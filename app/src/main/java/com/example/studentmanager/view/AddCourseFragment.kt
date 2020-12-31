package com.example.studentmanager.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.R
import com.example.studentmanager.model.Course
import com.example.studentmanager.viewmodel.CoursesViewModel
import kotlinx.android.synthetic.main.fragment_add_course.*
import kotlinx.android.synthetic.main.fragment_add_course.view.*

class AddCourseFragment : Fragment() {
    private lateinit var viewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_course, container, false)

        viewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)

        view.addButton.setOnClickListener {
            insertDataToDatabase()

            val activityHelper = ActivityHelper()
            activityHelper.closeKeyboard(activity)
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = editTextCourseName.text.toString()
        val note = editTextCourseNote.text.toString()

        if (name.isNotEmpty() && note.isNotEmpty()){
            val course = Course(0, name, note)
            viewModel.addCourse(course)

            Toast.makeText(requireContext(), getString(R.string.toast_succes), Toast.LENGTH_SHORT).show()

            findNavController().navigateUp()
        }
        else {
            Toast.makeText(requireContext(), getText(R.string.warning_input_incorrect), Toast.LENGTH_SHORT).show()
        }
    }
}