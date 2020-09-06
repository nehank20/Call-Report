package com.poilkar.nehank.callreport.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.poilkar.nehank.callreport.model.CallLogModel


@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCallLog(callLogModel: CallLogModel): Long

    @Query("SELECT * FROM CallLogModel")
    fun getSavedCallsLog() : LiveData<List<CallLogModel>>

    @Delete()
    suspend fun deleteCallLog(callLogModel: CallLogModel)

    @Query("DELETE FROM CallLogModel")
    suspend fun deleteAllEntries()


    // return list for saving in firebase
    @Query("SELECT * FROM CallLogModel")
    suspend fun getSavedCallsLogs() : List<CallLogModel>

}