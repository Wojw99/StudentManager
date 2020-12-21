package com.example.studentmanager.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.studentmanager.data.StudentDao
import com.example.studentmanager.model.Course
import com.example.studentmanager.model.CourseStudentCrossRef
import com.example.studentmanager.model.CourseWithStudents
import com.example.studentmanager.model.Student

/**
 * A repository class abstracts access to multiple data sources
 * */
class StudentRepository(private val studentDao: StudentDao) {
    val readAllData: LiveData<List<Student>> = studentDao.readAllStudents()
    val readAllCourseWithStudents: LiveData<List<CourseWithStudents>> = studentDao.getAllCourseWithStudents()

    suspend fun addStudentToCourse(studentId: Int, courseId: Int){
        studentDao.addStudentCourseCrossRef(CourseStudentCrossRef(studentId, courseId))
    }

    suspend fun addStudent(student: Student){
        studentDao.addStudent(student)
    }

    suspend fun updateStudent(student: Student){
        studentDao.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student){
        studentDao.deleteStudent(student)
    }

    suspend fun deleteAllStudents(){
        studentDao.deleteAllStudents()
    }
}