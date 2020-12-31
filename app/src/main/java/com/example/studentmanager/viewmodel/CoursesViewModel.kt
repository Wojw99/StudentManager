package com.example.studentmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.studentmanager.data.StudentDatabase
import com.example.studentmanager.model.Course
import com.example.studentmanager.model.CourseWithStudents
import com.example.studentmanager.model.Student
import com.example.studentmanager.repository.CourseRepository
import com.example.studentmanager.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoursesViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Course>>
    val readAllCourseWithStudents: LiveData<List<CourseWithStudents>>

    private val repository: CourseRepository

    init{
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = CourseRepository(studentDao)
        readAllData = repository.readAllData
        readAllCourseWithStudents = repository.readAllCourseWithStudents
    }

    fun addStudentToCourse(studentId: Int, courseId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudentToCourse(studentId, courseId)
        }
    }

    /**
     * Add course to the database in a background thread
     * */
    fun addCourse(course: Course){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCourse(course)
        }
    }

    fun updateCourse(course: Course){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCourse(course)
        }
    }

    fun removeCourse(course: Course){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCourse(course)
        }
    }
}