package com.example.studentmanager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "grade_table")
data class Grade(
        @PrimaryKey(autoGenerate = true)
        val gradeId: Int,
        val ownerId: Int,
        val name: String,
        val date: Date,
        val note: String,
        val course: String = "None"
): Parcelable