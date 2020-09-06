package com.poilkar.nehank.callreport.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.poilkar.nehank.callreport.R
import com.poilkar.nehank.callreport.ui.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit


/*

MD5: EF:97:71:05:B5:C0:52:FC:32:F0:4C:6A:D3:DF:8C:98
SHA1: 8B:A5:68:40:41:7B:DF:20:63:40:58:8A:6D:78:CE:8A:EF:C8:18:A6
SHA-256: DD:26:88:24:E1:C1:A1:66:E9:80:FF:A3:90:BA:AB:98:70:37:86:EC:AC:32:79:F2:C3:E7:1E:90:F6:C0:5F:75

 */

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnNext.setOnClickListener {
            if(etName.text.toString().trim().isNullOrEmpty() && etNumber.text.toString().trim().isNullOrEmpty()){
                if(etName.text.toString().trim().isNullOrEmpty()){
                    etName.error = "Please enter username"
                }

                if(etNumber.text.toString().trim().isNullOrEmpty()){
                    etNumber.error = "Please enter number"
                }
            }else{
                startActivity(Intent(this@LoginActivity, OtpActivity::class.java)
                    .putExtra("username",etName.text.toString().trim())
                    .putExtra("mobile",etNumber.text.toString().trim()))
            }
        }
    }

}