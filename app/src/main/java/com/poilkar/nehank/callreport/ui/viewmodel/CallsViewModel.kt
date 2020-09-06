package com.poilkar.nehank.callreport.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.poilkar.nehank.callreport.model.CallLogModel
import com.poilkar.nehank.callreport.repo.CallsRepository
import com.poilkar.nehank.callreport.util.Helperr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.set

class CallsViewModel(
    val app: Application, val callsRepository: CallsRepository
) : ViewModel() {


    val callRecordsList : MutableLiveData<ArrayList<CallLogModel>> = MutableLiveData()
    private val callsCollectionRef = Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.phoneNumber.toString())


    init {
        getSavedCallsLog()
    }

    fun insertCallRecordIntoFirestore() {
        val firestoreDb = FirebaseFirestore.getInstance()
        viewModelScope.launch {
            val listOfCalls = callsRepository.getSavedCallsInList()
            try {
                val callHashMap = HashMap<String, List<CallLogModel>>()
//                for (item in listOfCalls) {
//                    callHashMap[item.id.toString()] = item
//                }

                callHashMap["callsList"] = listOfCalls

                if (FirebaseAuth.getInstance().currentUser != null) {

                    callsCollectionRef.document(Helperr.getDateInStringFormat(Date()))
                        .set(callHashMap)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                println("Nehankk" + "saved")
                            } else {
                                println("Nehankk" + "false")
                            }
                        }
                }
            } catch (ex: Exception) {
                println("Nehankk" + ex)
            }
        }


    }

    fun getCallRecordsFromFirestore(selectedDate: String){
        val firestoreDb = FirebaseFirestore.getInstance()
        firestoreDb.collection(FirebaseAuth.getInstance().currentUser?.phoneNumber.toString())
            .document(selectedDate)
            .get()
            .addOnCompleteListener { task ->
                if(task.isSuccessful){

                    try{
                        val  querySnapshot= task.result
                        if (querySnapshot != null) {



//                            val listOfDocuments = querySnapshot.documents
//                            if(listOfDocuments.size > 0){
//                                for(items in listOfDocuments){
//
//                                    var arrayList = items.get(selectedDate)
//                                    callRecordsList.postValue(items.data.values.toList())
//                                }
//
//                            }

                        }
                    }catch (ex: Exception){
                        println(ex)
                    }

                }else{
                    println("failure")
                }
            }
    }

    fun insertCallLogs(callLogModel: CallLogModel) = viewModelScope.launch {
        callsRepository.insertCallLog(callLogModel)
    }

    fun deleteCallLogs(callLogModel: CallLogModel) = viewModelScope.launch {
        callsRepository.deleteCallLog(callLogModel)
    }

    fun deleteAllEntries() = viewModelScope.launch {
        callsRepository.deleteAllEntries()
    }

    fun getSavedCallsLog() = callsRepository.getSavedCallsInLiveData()


    fun getCallRecords(selectedDate: String) = CoroutineScope(Dispatchers.IO).launch {

        try{
            val documentSnapShot = callsCollectionRef.document(selectedDate).get().await()
            val listOfHashMap = documentSnapShot.get("callsList") as ArrayList<HashMap<String, Object>>
            val arrayList = ArrayList<CallLogModel>()
            for(item in listOfHashMap){
                var id = 0
                var number= ""
                var timestamp = ""
                for((key, value) in item){
                    when(key){
                        "number"->{
                            number = value as String
                        }
                        "id"->{
                            id = (value as Long).toInt()
                        }
                        "timestamp"->{
                            timestamp = value as String
                        }
                    }
                }
                val model = CallLogModel(number,timestamp)
                model.id = id
                println(model)
                arrayList.add(model)
            }

            if(arrayList.size > 0){
                callRecordsList.postValue(arrayList)
            }else{
                callRecordsList.postValue(null)
            }


//                for(document in querySnapShot.documents){
//                    if(document.id.equals(selectedDate)){
//
//                        val a = document.get("callsList") as List<Map<String, Object>>
//                        println(a)



//                        val hashmap = document.data as HashMap<String, Any>
//                        if(hashmap.size > 0){
//                            val arrayList = ArrayList<CallLogModel>()
//                            for((key, value) in hashmap){
//                                val callLogModel = value
//                                println(callLogModel)
//
//                                callRecordsList.postValue(value)
//                            }
//                            callRecordsList.postValue(arrayList)
//                        }else{
//                            callRecordsList.postValue(null)
//                        }



//
//                    }
//                }
//            }else{
//                callRecordsList.postValue(null)
//            }
        }catch (ex: Exception){
            callRecordsList.postValue(null)
        }


    }


}