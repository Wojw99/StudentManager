package com.example.studentmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.R
import com.example.studentmanager.model.Course
import com.example.studentmanager.model.StudentWithCourses

class StudentWithCoursesAdapter (var studentCoursesList: LiveData<List<StudentWithCourses>>, var studentId: Int)
    : RecyclerView.Adapter<StudentWithCoursesAdapter.Holder>() {
    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row_one_student_courses, parent, false)
        return Holder(
            view
        )
    }

    override fun getItemCount(): Int {
        //return courseStudentsList.value?.get(courseId)?.students?.size ?: 0
        studentCoursesList.value?.forEach {
            if (it.student.studentId == studentId){
                return it.courses.size
            }
        }

        return 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var currentItem = Course(0,"","")

        studentCoursesList.value?.forEach {
            if (it.student.studentId == studentId){
                currentItem = it.courses[position]
            }
        }

        val tvName = holder.itemView.findViewById<TextView>(R.id.tvCourseName)
        tvName.text = currentItem.name
    }
}