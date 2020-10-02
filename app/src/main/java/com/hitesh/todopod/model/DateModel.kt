package com.hitesh.todopod.model

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by raphael on 02.10.2020.
 * ToDoPod Created in com.hitesh.todopod.model
 */
class DateModel @Inject constructor(){

    fun date(): String{
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
        return formatter.format(date)
    }
}