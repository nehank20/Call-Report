package com.poilkar.nehank.callreport.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.poilkar.nehank.callreport.R
import com.poilkar.nehank.callreport.ui.HomeActivity

import kotlinx.android.synthetic.main.activity_otp.*
import java.util.concurrent.TimeUnit


class OtpActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var verificationCodeSent = ""
    var username = ""
    var mobileNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        firebaseLogin()
        val intent = intent
        username = intent.getStringExtra("username")
        mobileNumber = intent.getStringExtra("mobile")

        clickListeners()
        generateOtp("+91", mobileNumber)

    }

    private fun clickListeners(){
        btnGenerateOtp.setOnClickListener {
            generateOtp("+91", mobileNumber)
            btnGenerateOtp.visibility = View.GONE
        }

        btnSignIn.setOnClickListener {

            if(tvOtp.text.toString().trim().isNullOrEmpty()){
                tvOtp.error = "Please enter otp"
            }else{
//                if(!tvOtp.text.toString().trim().equals(verificationCodeSent)){
//                    tvOtp.error = "Code doesn't match"
//                }else{

                    val credential = PhoneAuthProvider.getCredential(verificationCodeSent, tvOtp.text.toString().trim())
                    signInWithPhoneAuthCredential(credential)
//                }
            }
        }
    }

    private fun generateOtp(countryCode: String, mobileNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            (countryCode + mobileNumber),
            60,
            TimeUnit.SECONDS,
            this,
            callbacks
        )
        startTimer(30000, 1000)

    }


    private fun startTimer(finish: Long, tick: Long) {
        val countdownTimer = object : CountDownTimer(finish, tick) {
            override fun onTick(millisUntilFinished: Long) {
                val remindSec = 1 / 1000
                timer.visibility = View.VISIBLE
                timer.text = "Retry after ${millisUntilFinished/1000} secs"
            }

            override fun onFinish() {
                btnGenerateOtp.text = "Re-Generate otp"
                btnGenerateOtp.visibility = View.VISIBLE
                Log.d("TAG", "onFinish: finish")

                timer.visibility = View.INVISIBLE
                cancel()
            }

        }.start()
    }

    private fun firebaseLogin() {
        auth = FirebaseAuth.getInstance()
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Toast.makeText(this@OtpActivity,"Verification Completed",Toast.LENGTH_SHORT).show()
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(this@OtpActivity,"Verification Failed",Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationCodeSent = p0
                Toast.makeText(this@OtpActivity,"Code sent",Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")

                    val user = task.result?.user
                    startActivity(Intent(this@OtpActivity, HomeActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                    finish()
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }
}