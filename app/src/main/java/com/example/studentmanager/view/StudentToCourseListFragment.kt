package com.example.studentmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanager.R
import com.example.studentmanager.adapters.CoursesAdapter
import com.example.studentmanager.adapters.CoursesAddAdapter
import com.example.studentmanager.viewmodel.CoursesViewModel
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_student_to_course_list.view.*

class StudentToCourseListFragment : Fragment() {
    private lateinit var coursesViewModel: CoursesViewModel
    private val args by navArgs<StudentToCourseListFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_to_course_list, container, false)

        coursesViewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)
        val adapter = CoursesAddAdapter(coursesViewModel, args.currentStudent.studentId)

        val recyclerView = view.recyclerViewCoursesToAdd
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        coursesViewModel.readAllData.observe(viewLifecycleOwner, Observer { course ->
            // observe when readAllData is changing and update data in the adapter
            adapter.setData(course)
        })

        return view
    }


}