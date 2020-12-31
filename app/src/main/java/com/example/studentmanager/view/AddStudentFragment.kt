package com.example.studentmanager.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.R
import com.example.studentmanager.model.Student
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_student_add.*
import kotlinx.android.synthetic.main.fragment_student_add.view.*
import kotlinx.android.synthetic.main.fragment_student_list.view.*

class AddStudentFragment : Fragment() {
    private lateinit var studentViewModel: StudentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_add, container, false)

        studentViewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)

        view.addButton.setOnClickListener {
            insertDataToDatabase()

            val activityHelper = ActivityHelper()
            activityHelper.closeKeyboard(activity)
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = editTextFirstName.text.toString()
        val lastName = editTextLastName.text.toString()
        val note = editTextGroup.text.toString()

        if (inputsCorrect(firstName, lastName)){
            val student = Student(0, firstName, lastName, note)
            studentViewModel.addStudent(student)

            Toast.makeText(requireContext(), getString(R.string.toast_succes), Toast.LENGTH_SHORT).show()

            findNavController().navigateUp()
        }
        else {
            Toast.makeText(requireContext(), R.string.warning_input_incorrect, Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputsCorrect(firstName: String, lastName: String): Boolean{
        if(firstName.isEmpty() || lastName.isEmpty()){
            return false
        }
        return true
    }
}