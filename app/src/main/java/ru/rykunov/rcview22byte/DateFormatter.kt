package ru.rykunov.rcview22byte

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun getDate(date: String): String{
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date: Date = format.parse(date)
        return date.toString()
    }

}