package com.example.studentmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.R
import com.example.studentmanager.model.CourseWithStudents
import com.example.studentmanager.model.Student
import com.example.studentmanager.view.OneCourseFragmentDirections
import com.example.studentmanager.view.StudentListFragmentDirections

class CourseWithStudentsAdapter (var courseStudentsList: LiveData<List<CourseWithStudents>>, var courseId: Int)
    : RecyclerView.Adapter<CourseWithStudentsAdapter.Holder>() {
    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_row_one_course_students, parent, false)
        return Holder(
            view
        )
    }

    override fun getItemCount(): Int {
        courseStudentsList.value?.forEach {
            if (it.course.courseId == courseId){
                return it.students.size
            }
        }

        return 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var currentItem = Student(0,"","","")

        courseStudentsList.value?.forEach {
            if (it.course.courseId == courseId){
                currentItem = it.students[position]
            }
        }

        val tvName = holder.itemView.findViewById<TextView>(R.id.textViewName)
        val tvValue = holder.itemView.findViewById<TextView>(R.id.textViewValue)
        val layout = holder.itemView.findViewById<LinearLayout>(R.id.layoutOneCourseStudents)

        val firstName = currentItem.firstName
        val lastName = currentItem.lastName
        val fullName = "$firstName $lastName"
        tvName.text = fullName
        tvValue.text = currentItem.group

        layout.setOnClickListener {
            // Navigate to OneStudentFragment with specific argument
            val action = OneCourseFragmentDirections
                    .actionOneCourseFragmentToOneStudentFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
}