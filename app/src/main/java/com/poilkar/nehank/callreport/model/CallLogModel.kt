package com.poilkar.nehank.callreport.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class CallLogModel(
    val number: String = "",
    val timestamp: String = "",

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}