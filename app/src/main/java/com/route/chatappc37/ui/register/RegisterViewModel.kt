package com.route.chatappc37.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.route.chatappc37.checkEmail
import com.route.chatappc37.database.addUser
import com.route.chatappc37.model.AppUser
import com.route.chatappc37.model.DataUtils
import com.route.chatappc37.ui.base.BaseViewModel

//sealed class RegisterIntents {
//    class SelectTab():RegisterIntents
//    class SelectTab():
////Intents
///*
//1- Select Tab (Select Source)
//2- Clicks on News (Navigates to Another screen)
//
// */
//}
//
//sealed class RegisterStates {
//object Loading : RegisterStates()
//class LoadedNews() : RegisterStates()
//object Idle : RegisterStates()
//class Error(val message: String) : RegisterStates()
///*
//// States
//1- State Loading -> Loading in progress and shows loading
//2- State Idle ->Action Selects index 0
//3- State LoadedTheNews -> Show it to the UI or XML
//4- State Error -> Show Dialog To User
// */
//
//
//}


//Intents
/*
1- Select Tab (Select Source)
2- Clicks on News (Navigates to Another screen)

// States
1- State Loading -> Loading in progress and shows loading
2- State Idle ->Action Selects index 0
3- State LoadedTheNews -> Show it to the UI or XML
4- State Error -> Show Dialog To User
 */
class RegisterViewModel : BaseViewModel<Navigator>() {

    val auth = Firebase.auth
    val email: MutableLiveData<String> = MutableLiveData()
    val emailError: MutableLiveData<String?> = MutableLiveData(null)
    val password: MutableLiveData<String> = MutableLiveData<String>()
    val passwordError: MutableLiveData<String?> = MutableLiveData(null)
    val firstName: MutableLiveData<String> = MutableLiveData()
    val firstNameError: MutableLiveData<String?> = MutableLiveData(null)
    val lastName: MutableLiveData<String> = MutableLiveData()
    val lastNameError: MutableLiveData<String?> = MutableLiveData(null)
    fun register() {
        if (validateInputs()) {
            createAccountOnFirebase()
        }

    }

    fun createAccountOnFirebase() {
        auth.createUserWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Log.e("Firebase", "createAccountOnFirebase: Success ${user?.email}")
                    val uid = task.result.user?.uid
                    addUserToFireStore(uid)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("Firebase", "createUserWithEmail:failure", task.exception)
                    messageLiveData.value = task.exception?.message
                }
            }
    }

    private fun addUserToFireStore(uid: String?) {
        showProgressBar.value = true
        val user = AppUser(uid, firstName.value, lastName.value, email.value)
        addUser(uid, user, onSuccessListener = {
            //navigate To Home
            showProgressBar.value = false
            DataUtils.user = user
            navigator?.navigateToHome()
        }, onFailureListener = {
            showProgressBar.value = false
            messageLiveData.value = it.message
        })
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
        if (firstName.value.isNullOrBlank()) {
            isValid = false
            firstNameError.value = "First Name Required"
        } else {
            firstNameError.value = null
        }
        if (lastName.value.isNullOrBlank()) {
            isValid = false
            lastNameError.value = "Last name Required"
        } else {
            lastNameError.value = null
        }
        return isValid

    }
}