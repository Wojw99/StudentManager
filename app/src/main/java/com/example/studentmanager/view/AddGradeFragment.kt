package com.example.studentmanager.view

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studentmanager.R
import com.example.studentmanager.model.Grade
import com.example.studentmanager.viewmodel.CoursesViewModel
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_add_grade.*
import kotlinx.android.synthetic.main.fragment_add_grade.view.*
import kotlinx.coroutines.launch
import java.util.*

class AddGradeFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private val args by navArgs<AddGradeFragmentArgs>()
    private lateinit var viewModel: StudentsViewModel
    private lateinit var viewModelCourses: CoursesViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_grade, container, false)

        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        viewModelCourses = ViewModelProvider(this).get(CoursesViewModel::class.java)

        view.addButton.setOnClickListener {
            insertDataToDatabase()

            val activityHelper = ActivityHelper()
            activityHelper.closeKeyboard(activity)
        }

        val dateHelper = DateHelper()
        view.buttonGradeDate.text = dateHelper.getTodayDateText()

        view.buttonGradeDate.setOnClickListener {
            val datePicker = DatePickerFragment(this)
            datePicker.show(childFragmentManager, "Date Picker")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{
            val values = viewModelCourses.getAllCourseNames()

            val arrayAdapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    values.toTypedArray())
            spinnerCourses.adapter = arrayAdapter
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val dateText = "$dayOfMonth.${month + 1}.$year"
        buttonGradeDate.text = dateText
    }

    private fun insertDataToDatabase(){
        val name = etGradeName.text.toString()
        val note = etGradeNote.text.toString()
        val course = spinnerCourses.selectedItem
        val dateArray = buttonGradeDate.text.split('.')
        val calendar = GregorianCalendar(dateArray[2].toInt(), dateArray[1].toInt(), dateArray[0].toInt())

        if(name.isNotEmpty() && course != null){
            viewModel.addGrade(Grade(0, args.currentStudent.studentId, name,
                    calendar.time, note, course.toString()))

            Toast.makeText(requireContext(), getString(R.string.toast_succes), Toast.LENGTH_SHORT).show()

            findNavController().navigateUp()
        }
        else if (course == null){
            Toast.makeText(requireContext(), R.string.warning_course_empty, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(requireContext(), R.string.warning_input_incorrect, Toast.LENGTH_SHORT).show()
        }
    }
}