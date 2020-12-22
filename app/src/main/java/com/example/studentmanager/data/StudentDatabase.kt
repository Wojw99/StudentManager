package com.example.studentmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentmanager.model.Course
import com.example.studentmanager.model.CourseStudentCrossRef
import com.example.studentmanager.model.Grade
import com.example.studentmanager.model.Student

/**
 * Contains the database holder and serves as the main access point for the underlying
 * connection to app's persisted, relational data
 * */
@Database(entities = [Student::class, Course::class, CourseStudentCrossRef::class, Grade::class], version = 1, exportSchema = false)
abstract class StudentDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object{
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase {
            val tempInstance =
                INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_database12"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}