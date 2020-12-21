package com.example.studentmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.R
import com.example.studentmanager.model.Student
import com.example.studentmanager.view.StudentListFragmentDirections
import kotlinx.android.synthetic.main.list_row.view.*

class StudentAdapter(var studentList: LiveData<List<Student>>): RecyclerView.Adapter<StudentAdapter.Holder>() {
    class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return studentList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = studentList.value?.get(position)
        val tvName = holder.itemView.findViewById<TextView>(R.id.textViewName)
        val tvValue = holder.itemView.findViewById<TextView>(R.id.textViewValue)
        //val btnDelete = holder.itemView.findViewById<Button>(R.id.buttonDelete)

        val firstName = currentItem?.firstName
        val lastName = currentItem?.lastName
        val fullName = "$firstName $lastName"
        tvName.text = fullName
        tvValue.text = currentItem?.group

        holder.itemView.buttonEdit.setOnClickListener {
            // Navigate to UpdateFragment with specific argument
            val action = currentItem?.let { it1 ->
                StudentListFragmentDirections
                        .actionStudentListFragmentToUpdateStudentFragment(it1)
            }
            if (action != null) {
                holder.itemView.findNavController().navigate(action)
            }
        }
    }
}