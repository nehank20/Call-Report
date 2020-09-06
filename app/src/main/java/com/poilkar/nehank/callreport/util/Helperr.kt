package com.poilkar.nehank.callreport.util

import android.content.Context
import android.widget.Toast
import com.poilkar.nehank.callreport.util.Constants.SIMPLE_DATE_FORMATTER_TYPE
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Helperr{

    fun getDateInStringFormat(date: Date): String{
        val formatter: DateFormat = SimpleDateFormat(SIMPLE_DATE_FORMATTER_TYPE)
        return formatter.format(date)
    }

    fun getDateInDateFormat(strInDate: String): Date {
        val formatter1: DateFormat
        formatter1 = SimpleDateFormat("SIMPLE_DATE_FORMATTER_TYPE")
        return formatter1.parse(strInDate) as Date
    }

    fun showToast(context: Context, msg: String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    fun getCurrentTime(): String {
        val df: DateFormat = SimpleDateFormat("h:mm a") // Format time
        return df.format(Calendar.getInstance().time)
    }

}