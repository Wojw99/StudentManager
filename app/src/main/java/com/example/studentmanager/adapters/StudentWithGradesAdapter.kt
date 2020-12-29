package com.example.studentmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.R
import com.example.studentmanager.model.Course
import com.example.studentmanager.model.Grade
import com.example.studentmanager.model.StudentWithGrades
import com.example.studentmanager.view.DateHelper
import java.util.*

class StudentWithGradesAdapter (var studentGradesList: LiveData<List<StudentWithGrades>>, var studentId: Int)
    : RecyclerView.Adapter<StudentWithGradesAdapter.Holder>() {
    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row_one_student_grades, parent, false)
        return Holder(
            view
        )
    }

    override fun getItemCount(): Int {
        //return courseStudentsList.value?.get(courseId)?.students?.size ?: 0
        studentGradesList.value?.forEach {
            if (it.student.studentId == studentId){
                return it.grades.size
            }
        }

        return 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var currentItem = Grade(0,0,"", GregorianCalendar(2020,10,2).time,"")

        studentGradesList.value?.forEach {
            if (it.student.studentId == studentId){
                currentItem = it.grades[position]
            }
        }

        val dateHelper = DateHelper()

        val tvName = holder.itemView.findViewById<TextView>(R.id.tvGradeName)
        val tvDate = holder.itemView.findViewById<TextView>(R.id.tvGradeDate)
        val tvNote = holder.itemView.findViewById<TextView>(R.id.tvGradeNote)
        tvName.text = currentItem.name
        tvDate.text = dateHelper.getDateText(currentItem.date)
        tvNote.text = currentItem.note
    }
}