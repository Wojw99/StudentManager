package com.example.studentmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanager.R
import com.example.studentmanager.adapters.CourseWithStudentsAdapter
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_students_with_course_list.*


class StudentsWithCourseListFragment : Fragment() {
    private val args by navArgs<UpdateCourseFragmentArgs>()
    private lateinit var viewModel: StudentsViewModel
    private lateinit var adapter: CourseWithStudentsAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_students_with_course_list, container, false)

        // ViewModel
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        adapter = CourseWithStudentsAdapter(
            viewModel.readAllCourseWithStudents,
            args.currentCourse.courseId
        )

        viewModel.readAllCourseWithStudents.observe(viewLifecycleOwner, Observer { _ ->
            // observe when readAllData is changing and update data in the adapter
            adapter.notifyDataSetChanged()
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleViewSINC.layoutManager = LinearLayoutManager(requireContext())
        recycleViewSINC.adapter = adapter
    }
}