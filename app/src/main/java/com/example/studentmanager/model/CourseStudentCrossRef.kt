package com.example.studentmanager.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["studentId", "courseId"], tableName = "course_student_table")
data class CourseStudentCrossRef (
    val studentId: Int,
    val courseId: Int
)