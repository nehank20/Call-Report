package com.poilkar.nehank.callreport.db

import androidx.room.TypeConverter
import com.google.firebase.Timestamp
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }





}