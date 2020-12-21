package com.example.studentmanager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "grade_table")
data class Grade(
        @PrimaryKey(autoGenerate = true)
        val gradeId: Int,
        val name: String,
        val date: String,
        val note: String
): Parcelable