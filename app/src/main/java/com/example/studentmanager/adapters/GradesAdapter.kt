package com.example.studentmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.R
import com.example.studentmanager.model.Grade
import com.example.studentmanager.view.DateHelper
import kotlinx.android.synthetic.main.list_row_grades.view.*

class GradesAdapter(var gradeList: List<Grade>): RecyclerView.Adapter<GradesAdapter.Holder>(){
    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row_grades, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return gradeList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentGrade = gradeList[position]
        val tvName = holder.itemView.tvGradeName
        val tvDate = holder.itemView.tvGradeDate
        val tvNote = holder.itemView.tvGradeNote
        val tvCourse = holder.itemView.tvGradeCourse

        val dateHelper = DateHelper()

        tvName.text = currentGrade.name
        tvDate.text = dateHelper.getDateText(currentGrade.date)
        tvNote.text = currentGrade.note
        tvCourse.text = currentGrade.course
    }

}