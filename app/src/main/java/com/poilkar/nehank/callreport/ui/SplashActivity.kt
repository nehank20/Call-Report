package com.poilkar.nehank.callreport.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.google.firebase.auth.FirebaseAuth
import com.poilkar.nehank.callreport.R
import com.poilkar.nehank.callreport.ui.login.LoginActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val date = Calendar.getInstance().time
        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")


        val dateInString = formatter.format(date)
        println("Date in string : $dateInString")


        val formatter1: DateFormat
        formatter1 = SimpleDateFormat("dd/MM/yyyy")
        val dateInDate = formatter1.parse(dateInString) as Date
        println("Date in Date : $dateInDate")


        Handler().postDelayed(2000){

            if(FirebaseAuth.getInstance().currentUser == null){
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }else{
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            }



            finish()
        }



    }
}