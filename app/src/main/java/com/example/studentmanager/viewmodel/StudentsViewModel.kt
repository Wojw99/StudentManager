package com.example.studentmanager.viewmodel

import android.app.Application
import android.os.Debug
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.studentmanager.model.Student
import com.example.studentmanager.data.StudentDatabase
import com.example.studentmanager.model.Course
import com.example.studentmanager.model.CourseStudentCrossRef
import com.example.studentmanager.model.CourseWithStudents
import com.example.studentmanager.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Provides data to the UI and survive configuration changes. It's a communication
 * center between the Repository and the UI.
 * TIP: ViewModels should not hold a reference to views, activities, fragments
 * and context (except of application context in some cases).
 * */
class StudentsViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Student>>
    val readAllCourseWithStudents: LiveData<List<CourseWithStudents>>

    private val repository: StudentRepository

    init{
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        readAllData = repository.readAllData
        readAllCourseWithStudents = repository.readAllCourseWithStudents
    }

    fun addStudentToCourse(studentId: Int, courseId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudentToCourse(studentId, courseId)
        }
    }

    fun addStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }

    fun updateStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStudent(student)
        }
    }

    fun removeStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStudent(student)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllStudents()
        }
    }
}