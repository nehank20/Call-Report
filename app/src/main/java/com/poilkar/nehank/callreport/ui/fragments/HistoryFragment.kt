package com.poilkar.nehank.callreport.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.poilkar.nehank.callreport.R
import com.poilkar.nehank.callreport.adapter.CallsAdapter
import com.poilkar.nehank.callreport.ui.HomeActivity
import com.poilkar.nehank.callreport.ui.viewmodel.CallsViewModel
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment(R.layout.fragment_history) {

    lateinit var viewModel: CallsViewModel
    lateinit var callsAdapter: CallsAdapter

    private val callsCollectionRef = Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.phoneNumber.toString())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivBack.setOnClickListener {

        }

        viewModel = (activity as HomeActivity).viewModel
        viewModel.getCallRecords(arguments?.getString("selectedDate").toString())

        viewModel.callRecordsList.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE
            if(it == null){
                tvNoRecordsToDisplay.visibility = View.VISIBLE
                rvRemoteList.visibility = View.GONE
            }else{

                if(it.size > 0){
                    rvRemoteList.visibility = View.VISIBLE
                    tvNoRecordsToDisplay.visibility = View.GONE
                    callsAdapter = CallsAdapter(it)
                    rvRemoteList.apply {
                        adapter = callsAdapter
                        layoutManager = LinearLayoutManager(activity)
                    }
                }
            }


        })





    }

}