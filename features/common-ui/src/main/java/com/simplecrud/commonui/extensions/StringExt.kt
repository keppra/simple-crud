package com.simplecrud.commonui.extensions

import java.lang.Exception
import java.time.LocalDate

fun String.fromDateToStringResource(): DateFormatted {
    return try {
        val date = LocalDate.parse(this)
        val day = date.dayOfMonth.toString()
        val month = date.month.value
        val year = date.year.toString()
        if (date.dayOfMonth == 1 && date.month.value == 1 && date.year == 1) {
            DateFormatted(false)
        } else {
            DateFormatted(true, day, month.toMonthResource(), year)
        }
    } catch (exception: Exception) {
        DateFormatted(false)
    }
}

data class DateFormatted(
    val isCorrect: Boolean,
    val day: String? = "",
    val monthResource: Int? = 0,
    val year: String? = ""
)