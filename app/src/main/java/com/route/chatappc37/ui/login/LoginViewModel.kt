package com.route.chatappc37.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatappc37.checkEmail
import com.route.chatappc37.ui.base.BaseViewModel

class LoginViewModel : BaseViewModel<Navigator>() {

    val email: MutableLiveData<String> = MutableLiveData()
    val emailError: MutableLiveData<String?> = MutableLiveData(null)
    val password: MutableLiveData<String> = MutableLiveData<String>()
    val passwordError: MutableLiveData<String?> = MutableLiveData(null)
    val auth = Firebase.auth
    fun signIn() {
        if (validateInputs()) {
            authEmailAndPassword()
        }
    }

    fun authEmailAndPassword() {
        auth.signInWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
                    Log.e("Firebase", "authEmailAndPassword: Success")
                    navigator?.navigateToHome()
                } else {
                    // If sign in fails, display a message to the user.
                    messageLiveData.value = task.exception?.message//"Invalid email or password"
                }
            }
    }

    fun validateInputs(): Boolean {
        Log.e("TAG", "CALLED")
        var isValid: Boolean = true
        if (email.value.isNullOrBlank()) {
            //Show Error in View
            isValid = false
            emailError.value = "Email is blank"

        } else if (!checkEmail(email.value!!)) {
            //show Error
            emailError.value = "Invalid Email Address"
        } else {
            //Remove Error
            emailError.value = null

        }
        if (password.value.isNullOrBlank()) {
            isValid = false
            passwordError.value = "Password Required"
        } else if (password.value?.length!! < 6) {
            isValid = false
            passwordError.value = "Password Should more than 6 characters"
        } else {
            passwordError.value = null
        }
        return isValid

    }
}