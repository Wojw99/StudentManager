package com.example.studentmanager.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CourseWithStudents(
        @Embedded val course: Course,
        @Relation(
                parentColumn = "courseId",
                entityColumn = "studentId",
                associateBy = Junction(CourseStudentCrossRef::class)
        )
        val students: List<Student>
)