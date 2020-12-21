package com.example.studentmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.studentmanager.R
import com.example.studentmanager.data.StudentDao
import com.example.studentmanager.data.StudentDatabase
import com.example.studentmanager.model.Course
import com.example.studentmanager.model.CourseStudentCrossRef
import com.example.studentmanager.model.Student
import com.example.studentmanager.repository.StudentRepository
import com.example.studentmanager.viewmodel.CoursesViewModel
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var studentViewModel: StudentsViewModel
    private lateinit var courseViewModel: CoursesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController((R.id.fragment)))
        studentViewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        courseViewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)

        //makeSampleEntities()
    }

    private fun makeSampleEntities(){
        val students = listOf(
                Student(0, "Mark", "Damil", "Note"),
                Student(0, "Harry", "Cevil", "Note"),
                Student(0, "Norman", "Beduss", "Note"),
                Student(0, "Stephan", "Nowak", "Note"),
                Student(0, "Bruce", "Wollus", "Note")
        )

        students.forEach {
            studentViewModel.addStudent(it)
        }

        val courses = listOf(
                Course(0, "Analiza matematyczna", "Przedmiot prowadzony na Politechnice Gdańskiej"),
                Course(0, "Aplikacje mobilne", "Bootcamp Wyjavowani")
        )

        courses.forEach {
            courseViewModel.addCourse(it)
        }

        val references = listOf(
                CourseStudentCrossRef( 1, 2),
                CourseStudentCrossRef( 2, 2),
                CourseStudentCrossRef( 3, 1),
                CourseStudentCrossRef( 4, 1),
                CourseStudentCrossRef( 5, 1)
        )

        references.forEach {
            studentViewModel.addStudentToCourse(it.studentId, it.courseId)
        }
    }

    // back arrow action setup
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}