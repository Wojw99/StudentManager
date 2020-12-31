package com.example.studentmanager.repository

import androidx.lifecycle.LiveData
import com.example.studentmanager.data.StudentDao
import com.example.studentmanager.model.Course
import com.example.studentmanager.model.CourseStudentCrossRef
import com.example.studentmanager.model.CourseWithStudents
import com.example.studentmanager.model.Student

class CourseRepository(private val studentDao: StudentDao) {
    val readAllData: LiveData<List<Course>> = studentDao.readAllCourses()
    val readAllCourseWithStudents: LiveData<List<CourseWithStudents>> = studentDao.getAllCourseWithStudents()

    suspend fun addStudentToCourse(studentId: Int, courseId: Int){
        studentDao.addStudentCourseCrossRef(CourseStudentCrossRef(studentId, courseId))
    }

    suspend fun addCourse(course: Course){
        studentDao.addCourse(course)
    }

    suspend fun updateCourse(course: Course){
        studentDao.updateCourse(course)
    }

    suspend fun deleteCourse(course: Course){
        studentDao.deleteCourse(course)
    }
}