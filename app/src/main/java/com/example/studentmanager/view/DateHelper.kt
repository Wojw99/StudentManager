package com.example.studentmanager.view

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class DateHelper {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodayDateText(): String{
        val todayTime = LocalDateTime.now()
        val todayDate = Date.from(todayTime.atZone(ZoneId.systemDefault()).toInstant())
        return getDateText(todayDate)
    }

    fun getDateText(date: Date): String{
        val calendar = GregorianCalendar()
        calendar.time = date
        return "${calendar.get(Calendar.DAY_OF_MONTH)}.${calendar.get(Calendar.MONTH) + 1}.${calendar.get(Calendar.YEAR)}"
    }
}