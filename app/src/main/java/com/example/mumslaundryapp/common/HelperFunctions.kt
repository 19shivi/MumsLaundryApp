package com.example.mumslaundryapp.common

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date

fun showToastMessage(context: Context, message:String)
{
    Toast.makeText(context,message, Toast.LENGTH_LONG).show()
}
fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}
fun matchesString(str1:String,str2:String):Boolean{
    return str1.equals(str2)
}
fun formatter(n: Long) =
    DecimalFormat("#,###")
        .format(n)


fun getDate(timeStamp:Long):String{
    val formatter = SimpleDateFormat("dd/MM/yy")

    val calendar: Calendar = Calendar.getInstance()
    calendar.setTimeInMillis(timeStamp)
    return  formatter.format(calendar.getTime())
}
fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}
fun getThisWeekSunday():Long{
    val calendar = Calendar.getInstance()
    calendar.firstDayOfWeek = Calendar.SUNDAY
    calendar.time = Date()

    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.timeInMillis
}

fun getLastWeekSunday():Long{
    val calendar = Calendar.getInstance()
    calendar.firstDayOfWeek = Calendar.SUNDAY
    calendar.time = Date()
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    calendar.add(Calendar.WEEK_OF_YEAR, -1)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.timeInMillis
}
@RequiresApi(Build.VERSION_CODES.O)
fun getThisMonth():Long{
    val currentDate = LocalDate.now()
    val firstDayOfCurrentMonth = currentDate.withDayOfMonth(1)
    val timestampOfFirstDayOfCurrentMonth = firstDayOfCurrentMonth.atStartOfDay(ZoneOffset.UTC).toEpochSecond() * 1000
return timestampOfFirstDayOfCurrentMonth
}

@RequiresApi(Build.VERSION_CODES.O)
fun getLastMonth():Long{
    val currentDate = LocalDate.now()
    val firstDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1)
    val timestampOfFirstDayOfPreviousMonth = firstDayOfPreviousMonth.atStartOfDay(ZoneOffset.UTC).toEpochSecond() * 1000
    return timestampOfFirstDayOfPreviousMonth
}

@RequiresApi(Build.VERSION_CODES.O)
fun getThisYear():Long{
    val currentDate = LocalDate.now()
    val firstDayOfCurrentYear = LocalDate.of(currentDate.year, 1, 1)
    val timestampOfFirstDayOfCurrentYear = firstDayOfCurrentYear.atStartOfDay(ZoneOffset.UTC).toEpochSecond() * 1000
    return timestampOfFirstDayOfCurrentYear
}

@RequiresApi(Build.VERSION_CODES.O)
fun getLastYear():Long{
    val currentDate = LocalDate.now()
    val firstDayOfPreviousYear = LocalDate.of(currentDate.year - 1, 1, 1)
    val timestampOfFirstDayOfPreviousYear = firstDayOfPreviousYear.atStartOfDay(ZoneOffset.UTC).toEpochSecond() * 1000
    return timestampOfFirstDayOfPreviousYear
}
