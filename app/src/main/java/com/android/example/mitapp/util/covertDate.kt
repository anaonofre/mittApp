package com.android.example.mitapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {

    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    val dateString = dateFormat.format(systemTime)
    return dateString
}