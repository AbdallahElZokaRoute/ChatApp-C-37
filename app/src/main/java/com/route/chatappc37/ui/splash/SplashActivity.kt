package com.route.chatappc37.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatappc37.R
import com.route.chatappc37.model.DataUtils
import com.route.chatappc37.ui.home.HomeActivity
import com.route.chatappc37.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            checkIfUserLoggedIn()
            //            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            //            startActivity(intent)
        }, 2000)
    }

    private fun checkIfUserLoggedIn() {
        val user = Firebase.auth.currentUser
        if (user == null) {
            //      Start Login Activity
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        } else {
            //Start Home Activity
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}