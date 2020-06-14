package com.hitesh.todopod.model

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class DateModel : ViewModel() {
    fun date(): String{
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
        return formatter.format(date)
    }
}