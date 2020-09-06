package com.poilkar.nehank.callreport.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.poilkar.nehank.callreport.repo.CallsRepository

class CallsViewModelProviderFactory(val app: Application, val callsRepository: CallsRepository) :
ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CallsViewModel(app,callsRepository) as T
    }
}