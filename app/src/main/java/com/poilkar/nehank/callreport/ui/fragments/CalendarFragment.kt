package com.poilkar.nehank.callreport.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.measurement.AppMeasurement.Param.TIMESTAMP
import com.google.android.gms.measurement.module.Analytics.Param.TIMESTAMP
import com.google.firebase.firestore.FieldValue
import com.google.firestore.v1.DocumentTransform
import com.poilkar.nehank.callreport.R
import com.poilkar.nehank.callreport.model.CallLogModel
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.sql.Types.TIMESTAMP

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            println("$dayOfMonth-${month+1}-$year")

            val bundle = Bundle().apply {
                putString("selectedDate","$dayOfMonth-${month+1}-$year")
            }
            findNavController().navigate(R.id.action_calendarFragment_to_historyFragment, bundle)
        }

    }

}