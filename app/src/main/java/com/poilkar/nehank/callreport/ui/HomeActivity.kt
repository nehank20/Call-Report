package com.poilkar.nehank.callreport.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.poilkar.nehank.callreport.R
import com.poilkar.nehank.callreport.db.AppDatabase
import com.poilkar.nehank.callreport.model.CallLogModel
import com.poilkar.nehank.callreport.repo.CallsRepository
import com.poilkar.nehank.callreport.ui.viewmodel.CallsViewModel
import com.poilkar.nehank.callreport.ui.viewmodel.CallsViewModelProviderFactory
import com.poilkar.nehank.callreport.util.Helperr.getCurrentTime
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    lateinit var viewModel: CallsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val callsRepository = CallsRepository(AppDatabase(this))
        val callsRepositoryFactory = CallsViewModelProviderFactory(application, callsRepository)
        viewModel = ViewModelProvider(this, callsRepositoryFactory).get(CallsViewModel::class.java)
        

//        val callLogModel = CallLogModel("9920386497", getCurrentTime())
//        val callLogModel1 = CallLogModel("9956874102", getCurrentTime())
//        val callLogModel2 = CallLogModel("8450677721", getCurrentTime())
//        val callLogModel3 = CallLogModel("9752214600", getCurrentTime())
//        val callLogModel4 = CallLogModel("9851834610", getCurrentTime())
//        val callLogModel5 = CallLogModel("9964144004", getCurrentTime())
//        viewModel.insertCallLogs(callLogModel)
//        viewModel.insertCallLogs(callLogModel1)
//        viewModel.insertCallLogs(callLogModel2)
//        viewModel.insertCallLogs(callLogModel3)
//        viewModel.insertCallLogs(callLogModel4)
//        viewModel.insertCallLogs(callLogModel5)










        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
    }



}