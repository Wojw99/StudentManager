package com.example.studentmanager.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentmanager.R
import com.example.studentmanager.adapters.CourseWithStudentsAdapter
import com.example.studentmanager.viewmodel.CoursesViewModel
import kotlinx.android.synthetic.main.fragment_one_course.*


class OneCourseFragment : Fragment() {
    private val args by navArgs<OneCourseFragmentArgs>()
    private lateinit var viewModel: CoursesViewModel
    private lateinit var adapter: CourseWithStudentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one_course, container, false)

        // ViewModel
        viewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)
        adapter = CourseWithStudentsAdapter(
            viewModel.readAllCourseWithStudents,
            args.currentCourse.courseId
        )

        viewModel.readAllCourseWithStudents.observe(viewLifecycleOwner, Observer { _ ->
            // observe when readAllCourseWithStudents is changing and update data in the adapter
            adapter.notifyDataSetChanged()
        })

        return view
    }

    override fun onResume() {
        super.onResume()
        tvCourseName.text = args.currentCourse.name
        tvCourseNote.text = args.currentCourse.note
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Turn on options menu in this fragment
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate menu
        inflater.inflate(R.menu.course_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle menu item clicks
        val id = item.itemId

        if(id == R.id.itemEdit){
            editItem()
        }
        else if (id == R.id.itemRemove){
            deleteItem()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun editItem(){
        // Navigate to an update fragment
        val action = OneCourseFragmentDirections
            .actionOneCourseFragmentToUpdateCourseFragment(args.currentCourse)
        findNavController().navigate(action)
    }

    private fun deleteItem(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            viewModel.removeCourse(args.currentCourse)

            val message = "Successfully removed ${args.currentCourse.name}"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            findNavController().navigateUp()
        }
        builder.setNegativeButton("No"){ _, _ -> }
        builder.setTitle("Delete ${args.currentCourse.name}?")
        builder.setMessage("Are You sure you want to delete ${args.currentCourse.name}? The operation is permanent.")
        builder.create().show()
    }

}