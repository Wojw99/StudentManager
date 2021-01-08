package com.example.studentmanager.view

import android.app.AlertDialog
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
import com.example.studentmanager.model.Student
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_update_student.*
import kotlinx.android.synthetic.main.fragment_update_student.view.*

class UpdateStudentFragment : Fragment() {
    private val args by navArgs<UpdateStudentFragmentArgs>()
    private lateinit var viewModel: StudentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_update_student, container, false)

        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)

        view.updateTextFirstName.setText(args.currentStudent.firstName)
        view.updateTextLastName.setText(args.currentStudent.lastName)
        view.updateTextGroup.setText(args.currentStudent.group)

        view.updateButton.setOnClickListener {
            updateItem()

            val activityHelper = ActivityHelper()
            activityHelper.closeKeyboard(activity)
        }

        return view
    }

    private fun updateItem(){
        val firstName = updateTextFirstName.text.toString()
        val lastName = updateTextLastName.text.toString()
        val note = updateTextGroup.text.toString()

        if(inputsCorrect(firstName, lastName)){
            val updatedStudent = Student(args.currentStudent.studentId, firstName, lastName, note)
            viewModel.updateStudent(updatedStudent)

            Toast.makeText(requireContext(), getText(R.string.toast_succes), Toast.LENGTH_SHORT).show()

            findNavController().navigateUp()
            findNavController().navigateUp()
        }
        else{
            Toast.makeText(requireContext(), getText(R.string.warning_input_incorrect), Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputsCorrect(firstName: String, lastName: String): Boolean{
        if(firstName.isEmpty() || lastName.isEmpty()){
            return false
        }
        return true
    }
}