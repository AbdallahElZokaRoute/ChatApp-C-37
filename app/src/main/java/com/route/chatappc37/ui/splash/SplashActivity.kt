package com.route.chatappc37.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatappc37.R
import com.route.chatappc37.database.getUser
import com.route.chatappc37.model.AppUser
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

    fun startLoginActivity() {
        //      Start Login Activity
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    fun startHomeActivity() {
        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun checkIfUserLoggedIn() {
        val user = Firebase.auth.currentUser
        if (user == null) {
            startLoginActivity()
        } else {
            //Start Home Activity
            getUser(user.uid, onSuccessListener = { doc ->
                val userObj = doc.toObject(AppUser::class.java)
                DataUtils.user = userObj
                startHomeActivity()
            }, onFailureListener = {
                startLoginActivity()
            })
        }
    }
}