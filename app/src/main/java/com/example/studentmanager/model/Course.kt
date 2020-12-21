package com.example.studentmanager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Represents one object from the database
 * Parcelable lets us write the object into a parcel which can be moved between fragments.
 * */
@Parcelize
@Entity(tableName = "course_table")
data class Course (
    @PrimaryKey(autoGenerate = true)
    val courseId: Int,
    val name: String,
    val note: String
): Parcelable