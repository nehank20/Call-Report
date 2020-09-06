package com.poilkar.nehank.callreport.receiver

import android.annotation.SuppressLint
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.poilkar.nehank.callreport.db.AppDatabase
import com.poilkar.nehank.callreport.model.CallLogModel
import com.poilkar.nehank.callreport.repo.CallsRepository
import com.poilkar.nehank.callreport.ui.viewmodel.CallsViewModel
import com.poilkar.nehank.callreport.ui.viewmodel.CallsViewModelProviderFactory
import com.poilkar.nehank.callreport.util.Helperr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IncomingCallReceiver: BroadcastReceiver() {

    @SuppressLint("ServiceCast")
    override fun onReceive(context: Context?, intent: Intent?) {

        try{
            val state = intent!!.getStringExtra(TelephonyManager.EXTRA_STATE)
            val number = intent!!.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                if(number!= null){

                    CoroutineScope(Dispatchers.IO).launch {
                        val model = CallLogModel(number, Helperr.getCurrentTime())
                        val callsRepository = CallsRepository(AppDatabase(context!!))
                        callsRepository.db.getAppDao().insertCallLog(model)
                    }

                }
            }

        }catch (ex: Exception){
            Log.d("Nehankk", "Exception")
        }

    }
}