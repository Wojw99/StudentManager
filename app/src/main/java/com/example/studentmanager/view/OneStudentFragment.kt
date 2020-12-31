package com.example.studentmanager.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanager.R
import com.example.studentmanager.adapters.StudentWithCoursesAdapter
import com.example.studentmanager.adapters.StudentWithGradesAdapter
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_one_student.*
import kotlinx.android.synthetic.main.fragment_student_add.*

class OneStudentFragment : Fragment() {
    private val args by navArgs<OneStudentFragmentArgs>()
    private lateinit var viewModel: StudentsViewModel
    private lateinit var coursesAdapter: StudentWithCoursesAdapter
    private lateinit var gradesAdapter: StudentWithGradesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_one_student, container, false)

        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)

        coursesAdapter = StudentWithCoursesAdapter(
            viewModel.readAllStudentWithCourses,
            args.currentStudent.studentId,
            viewModel
        )
        gradesAdapter = StudentWithGradesAdapter(
            viewModel.readAllStudentWithGrades,
            args.currentStudent.studentId,
            viewModel
        )

        viewModel.readAllStudentWithCourses.observe(viewLifecycleOwner, Observer { _ ->
            coursesAdapter.notifyDataSetChanged()
        })
        viewModel.readAllStudentWithGrades.observe(viewLifecycleOwner, Observer { _ ->
            gradesAdapter.notifyDataSetChanged()
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewCourses.adapter = coursesAdapter
        recyclerViewCourses.layoutManager = LinearLayoutManager(requireContext())

        recyclerViewGrades.adapter = gradesAdapter
        recyclerViewGrades.layoutManager = LinearLayoutManager(requireContext())

        val fullName = "${args.currentStudent.firstName} ${args.currentStudent.lastName}"
        tvStudentName.text = fullName
        tvStudentNote.text = args.currentStudent.group
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Turn on options menu in this fragment
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate menu
        inflater.inflate(R.menu.student_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle menu item clicks

        when (item.itemId) {
            R.id.itemEdit -> editItem()
            R.id.itemRemove -> deleteItem()
            R.id.itemAddGrade -> addGrade()
            R.id.itemAddToCourse -> addToCourse()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addToCourse(){
        val action = OneStudentFragmentDirections
            .actionOneStudentFragmentToStudentToCourseListFragment(args.currentStudent)
        findNavController().navigate(action)
    }

    private fun addGrade(){
        // Navigate to AddGradeFragment
        val action = OneStudentFragmentDirections
            .actionOneStudentFragmentToAddGradeFragment(args.currentStudent)
        findNavController().navigate(action)
    }

    private fun editItem(){
        // Navigate to an update fragment
        val action = OneStudentFragmentDirections
            .actionOneStudentFragmentToUpdateStudentFragment(args.currentStudent)
        findNavController().navigate(action)
    }

    private fun deleteItem(){
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton(getString(R.string.alert_yes)){ _, _ ->
            viewModel.removeStudent(args.currentStudent)
            findNavController().navigateUp()
        }
        builder.setNegativeButton(getString(R.string.alert_no)){ _, _ -> }

        builder.setTitle(getText(R.string.alert_are_you_sure_title))
        builder.setMessage(getText(R.string.alert_are_you_sure))
        builder.create().show()
    }
}