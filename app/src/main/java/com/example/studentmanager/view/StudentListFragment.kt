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
import com.example.studentmanager.adapters.StudentAdapter
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.fragment_student_list.view.*

class StudentListFragment : Fragment() {
    private lateinit var viewModel: StudentsViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        // ViewModel
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        adapter =
            StudentAdapter(viewModel.readAllData)

        viewModel.readAllData.observe(viewLifecycleOwner, Observer { _ ->
            // observe when readAllData is changing and update data in the adapter
            adapter.notifyDataSetChanged()
        })

        view.faButton.setOnClickListener {
            findNavController().navigate(R.id.action_studentListFragment_to_studentAddFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
    }
}