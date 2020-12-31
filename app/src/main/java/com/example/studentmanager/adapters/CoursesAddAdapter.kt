package com.example.studentmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.R
import com.example.studentmanager.model.Course
import com.example.studentmanager.viewmodel.CoursesViewModel
import kotlinx.android.synthetic.main.list_row_course_add.view.*

class CoursesAddAdapter(var viewModel: CoursesViewModel, var studentId: Int): RecyclerView.Adapter<CoursesAddAdapter.Holder>()  {
    private var coursesList = emptyList<Course>()
    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row_course_add, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = coursesList[position]
        val tvName = holder.itemView.textViewName
        val tvLayout = holder.itemView.rowLayoutCourse

        tvName.text = currentItem.name

        tvLayout.setOnClickListener {
            viewModel.addStudentToCourse(studentId, currentItem.courseId)
            holder.itemView.findNavController().navigateUp()
        }
    }

    fun setData(list: List<Course>){
        this.coursesList = list
        notifyDataSetChanged()
    }
}