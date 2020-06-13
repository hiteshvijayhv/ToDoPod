package com.hitesh.todopod.model

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class DateModel : ViewModel() {
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
    val formatedDate = formatter.format(date)
}