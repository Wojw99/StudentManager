package com.example.studentmanager.model

import androidx.room.Embedded
import androidx.room.Relation

data class StudentWithGrades(
        @Embedded val student: Student,
        @Relation(
                parentColumn = "studentId",
                entityColumn = "ownerId"
        )
        val grades: List<Grade>
)