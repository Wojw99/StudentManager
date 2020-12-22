package com.example.studentmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.R
import com.example.studentmanager.model.Course
import com.example.studentmanager.view.CoursesListFragmentDirections

class CoursesAdapter: RecyclerView.Adapter<CoursesAdapter.Holder>() {
    private var coursesList = emptyList<Course>()

    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row_course, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = coursesList[position]
        val tvValue = holder.itemView.findViewById<TextView>(R.id.textViewValue)
        val tvNote = holder.itemView.findViewById<TextView>(R.id.textViewNote)
        val layout = holder.itemView.findViewById<LinearLayout>(R.id.rowLayoutCourse)

        tvValue.text = currentItem.name
        tvNote.text = currentItem.note

        layout.setOnClickListener {
            // Navigate to One Course Fragment with currentItem as argument
            val action = currentItem.let { it1 ->
                CoursesListFragmentDirections
                    .actionCoursesListFragmentToOneCourseFragment(it1)
            }
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(list: List<Course>){
        this.coursesList = list
        notifyDataSetChanged()
    }
}