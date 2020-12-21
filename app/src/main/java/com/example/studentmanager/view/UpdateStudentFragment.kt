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
        }

        view.deleteButton.setOnClickListener {
            deleteItem()
        }

        return view
    }

    private fun deleteItem(){
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){ _, _ ->
            viewModel.removeStudent(args.currentStudent)

            val message = "Successfully removed ${args.currentStudent.firstName} ${args.currentStudent.lastName}"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateStudentFragment_to_studentListFragment)
        }

        builder.setNegativeButton("No"){ _, _ -> }
        builder.setTitle("Delete ${args.currentStudent.firstName}?")
        builder.setMessage("Are You sure you want to delete ${args.currentStudent.firstName}? The operation is permanent.")
        builder.create().show()
    }

    private fun updateItem(){
        val firstName = updateTextFirstName.text.toString()
        val lastName = updateTextLastName.text.toString()
        val group = updateTextGroup.text.toString()

        if(inputsCorrect(firstName, lastName, group)){
            val updatedStudent = Student(args.currentStudent.studentId, firstName, lastName, group)
            viewModel.updateStudent(updatedStudent)

            Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateStudentFragment_to_studentListFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputsCorrect(firstName: String, lastName: String, group: String): Boolean{
        if(firstName.isEmpty() || lastName.isEmpty() || group.isEmpty()){
            return false
        }
        return true
    }
}