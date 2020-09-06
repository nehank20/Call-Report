package com.poilkar.nehank.callreport.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.poilkar.nehank.callreport.R
import com.poilkar.nehank.callreport.adapter.CallsAdapter
import com.poilkar.nehank.callreport.ui.HomeActivity
import com.poilkar.nehank.callreport.ui.login.LoginActivity
import com.poilkar.nehank.callreport.ui.viewmodel.CallsViewModel
import kotlinx.android.synthetic.main.fragment_calls_record.*

class CallsRecordFragment : Fragment(R.layout.fragment_calls_record) {


    lateinit var viewModel: CallsViewModel
    lateinit var callsAdapter: CallsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            activity?.finish()
        }

        viewModel = (activity as HomeActivity).viewModel

//        viewModel.insertCallRecordIntoFirestore()
        viewModel.getSavedCallsLog().observe(viewLifecycleOwner, Observer {

            callsAdapter = CallsAdapter(it)
            rvLocalList.apply {
                adapter = callsAdapter
                layoutManager = LinearLayoutManager(activity)
            }
        })
    }

}