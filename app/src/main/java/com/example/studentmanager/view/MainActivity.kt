package com.example.studentmanager.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.studentmanager.R
import com.example.studentmanager.data.StudentDao
import com.example.studentmanager.data.StudentDatabase
import com.example.studentmanager.model.Course
import com.example.studentmanager.model.CourseStudentCrossRef
import com.example.studentmanager.model.Grade
import com.example.studentmanager.model.Student
import com.example.studentmanager.repository.StudentRepository
import com.example.studentmanager.viewmodel.CoursesViewModel
import com.example.studentmanager.viewmodel.StudentsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var studentViewModel: StudentsViewModel
    private lateinit var courseViewModel: CoursesViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController((R.id.fragment)))
        studentViewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        courseViewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)

        //makeSampleEntities()

        lifecycleScope.launch {
            val todayTime = LocalDateTime.now()
            val yesterdayTime = LocalDateTime.now().minusDays(1)
            val todayDate = Date.from(todayTime.atZone(ZoneId.systemDefault()).toInstant())
            val yesterdayDate = Date.from(yesterdayTime.atZone(ZoneId.systemDefault()).toInstant())

            Log.d("Dzisiaj", todayDate.toString())

            val list = studentViewModel.getGradesBetweenDates(yesterdayDate, todayDate)
            list.forEach {
                Log.d("Dzisiaj", it.date.toString())
            }
        }
    }

    private fun makeSampleEntities(){
        val students = listOf(
                Student(0, "Mark", "Damil", "Szef koła naukowego"),
                Student(0, "Harry", "Cevil", ""),
                Student(0, "Norman", "Beduss", "Jest na ITS"),
                Student(0, "Stephan", "Nowak", "Dobrze sobie radzi"),
                Student(0, "Bruce", "Wollus", "...")
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
        
        val date1 = GregorianCalendar(2020,11,12)
        val date2 = GregorianCalendar(2020,11,12)
        val date3 = GregorianCalendar(2020,12,28)
        val date4 = GregorianCalendar(2020,12,28)

        studentViewModel.addStudentToCourse(1,1)
        studentViewModel.addStudentToCourse(1,2)
        studentViewModel.addGrade(Grade(0,1,"2.5", date1.time,"Postaraj się bardziej."))
        studentViewModel.addGrade(Grade(0,1,"4.5", date2.time,"Wyśmienicie sobie radzi."))
        studentViewModel.addGrade(Grade(0,1,"1.5", date3.time,"Bez komentarza."))
        studentViewModel.addGrade(Grade(0,2,"5.0", date4.time,"Wyżej już nie można."))
    }

    // back arrow action setup
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}