package com.example.studentmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanager.R
import com.example.studentmanager.adapters.CoursesAdapter
import com.example.studentmanager.viewmodel.CoursesViewModel
import kotlinx.android.synthetic.main.fragment_courses_list.view.*
import kotlinx.android.synthetic.main.fragment_student_list.view.recycleView

class CoursesListFragment : Fragment() {
    private lateinit var viewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_courses_list, container, false)

        // Recyclerview
        val adapter = CoursesAdapter()
        val recyclerView = view.recycleView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        viewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { course ->
            // observe when readAllData is changing and update data in the adapter
            adapter.setData(course)
        })

        view.faButtonCourse.setOnClickListener {
            findNavController().navigate(R.id.action_coursesListFragment_to_addCourseFragment)
        }

        return view
    }
}