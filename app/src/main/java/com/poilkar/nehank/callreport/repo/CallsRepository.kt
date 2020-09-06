package com.poilkar.nehank.callreport.repo

import com.poilkar.nehank.callreport.db.AppDatabase
import com.poilkar.nehank.callreport.model.CallLogModel

class CallsRepository(val db: AppDatabase) {

    suspend fun insertCallLog(callLogModel: CallLogModel) = db.getAppDao().insertCallLog(callLogModel)

    // returns livedata
    fun getSavedCallsInLiveData() = db.getAppDao().getSavedCallsLog()

    suspend fun deleteCallLog(callLogModel: CallLogModel) = db.getAppDao().deleteCallLog(callLogModel)

    suspend fun deleteAllEntries() = db.getAppDao().deleteAllEntries()

    // return list
    suspend fun getSavedCallsInList() = db.getAppDao().getSavedCallsLogs()

}