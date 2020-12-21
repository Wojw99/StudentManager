package com.example.studentmanager.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studentmanager.R
import com.example.studentmanager.model.Course
import com.example.studentmanager.viewmodel.CoursesViewModel
import kotlinx.android.synthetic.main.fragment_update_course.*
import kotlinx.android.synthetic.main.fragment_update_course.view.*
import kotlinx.android.synthetic.main.fragment_update_course.updateTextGroup
import kotlinx.android.synthetic.main.fragment_update_course.view.updateButton
import kotlinx.android.synthetic.main.fragment_update_course.view.updateTextGroup

class UpdateCourseFragment : Fragment() {
    private val args by navArgs<UpdateCourseFragmentArgs>()
    private lateinit var viewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_course, container, false)

        viewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)

        view.updateTextGroup.setText(args.currentCourse.name)
        view.updateTextNote.setText(args.currentCourse.note)

        view.updateButton.setOnClickListener {
            updateItem()

            val activityHelper = ActivityHelper()
            activityHelper.closeKeyboard(activity)
        }

        return view
    }

    private fun updateItem(){
        val name = updateTextGroup.text.toString()
        val note = updateTextNote.text.toString()

        if(name.isNotEmpty() && note.isNotEmpty()){
            val updatedCourse = Course(args.currentCourse.courseId, name, note)
            viewModel.updateCourse(updatedCourse)

            Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()

            findNavController().navigateUp()
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

}