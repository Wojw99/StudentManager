package com.example.studentmanager.view

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
import com.example.studentmanager.model.Grade
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_add_grade.*
import kotlinx.android.synthetic.main.fragment_add_grade.view.*
import java.util.*

class AddGradeFragment : Fragment() {
    private val args by navArgs<AddGradeFragmentArgs>()
    private lateinit var viewModel: StudentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_grade, container, false)

        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)

        view.addButton.setOnClickListener {
            insertDataToDatabase()

            val activityHelper = ActivityHelper()
            activityHelper.closeKeyboard(activity)
        }

        return view
    }

    private fun insertDataToDatabase(){
        val name = etGradeName.text.toString()
        val note = etGradeNote.text.toString()
        val calendar = GregorianCalendar(dpGrade.year, dpGrade.month, dpGrade.dayOfMonth)

        if(name.isNotEmpty()){
            viewModel.addGrade(Grade(0, args.currentStudent.studentId, name, calendar.time, note))

            Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()

            findNavController().navigateUp()
            findNavController().navigateUp()
        }
        else {
            Toast.makeText(requireContext(), R.string.warning_input_incorrect, Toast.LENGTH_SHORT).show()
        }
    }
}