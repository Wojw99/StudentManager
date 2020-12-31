package com.example.studentmanager.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.studentmanager.model.*
import java.util.*

/**
 * Contains methods used for accessing the database
 * */
@Dao
interface StudentDao {
    /* - - - - - - - Student <> Grade - - - - - - - */
    @Transaction
    @Query("SELECT * FROM student_table")
    fun getAllStudentWithGrades(): LiveData<List<StudentWithGrades>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // when is a new exactly the same user then we are going to ignore that
    suspend fun addGrade(grade: Grade)

    @Delete
    suspend fun deleteGrade(grade: Grade)

    @Query("SELECT * FROM grade_table WHERE date BETWEEN :from AND :to")
    suspend fun getGradesBetweenDates(from: Date, to: Date): List<Grade>

    /* - - - - - - - Student <> Course - - - - - - - */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudentCourseCrossRef(crossRef: CourseStudentCrossRef)

    @Delete
    suspend fun deleteStudentCourseCrossRef(crossRef: CourseStudentCrossRef)

    @Transaction
    @Query("SELECT * FROM course_table")
    fun getAllCourseWithStudents(): LiveData<List<CourseWithStudents>>

    @Transaction
    @Query("SELECT * FROM student_table")
    fun getAllStudentWithCourses(): LiveData<List<StudentWithCourses>>

    /* - - - - - - - Student - - - - - - - */
    @Insert(onConflict = OnConflictStrategy.IGNORE) // when is a new exactly the same user then we are going to ignore that
    suspend fun addStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM student_table")
    suspend fun deleteAllStudents()

    @Query("SELECT * FROM student_table ORDER BY studentId ASC") // get all user and order them
    fun readAllStudents(): LiveData<List<Student>>

    /* - - - - - - - Course - - - - - - - */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCourse(course: Course)

    @Update
    suspend fun updateCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)

    @Query("SELECT * FROM course_table ORDER BY courseId ASC") // get all user and order them
    fun readAllCourses(): LiveData<List<Course>>

}