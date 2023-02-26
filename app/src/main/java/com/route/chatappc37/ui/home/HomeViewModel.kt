package com.route.chatappc37.ui.home

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatappc37.database.getRooms
import com.route.chatappc37.database.getUser
import com.route.chatappc37.model.AppUser
import com.route.chatappc37.model.DataUtils
import com.route.chatappc37.model.Room
import com.route.chatappc37.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel<Navigator>() {

    val roomsList = MutableLiveData<List<Room>>()
    fun getUserFromFireStore() {
        getUser(Firebase.auth.currentUser?.uid!!, onSuccessListener = {
            DataUtils.user = it.toObject(AppUser::class.java)
        }, onFailureListener = {
            messageLiveData.value = it.message
        })

    }

    fun getRoomsFromFirestore() {
        showProgressBar.value = true
        getRooms(onSuccessListener = { snapshot ->
            showProgressBar.value = false
            roomsList.value = snapshot.toObjects(Room::class.java)
//            snapshot.documents.forEach { doc ->
//                doc.toObject(Room::class.java)
//            }
        }, onFailureListener = {
            showProgressBar.value = false
            messageLiveData.value = it.message
        })

    }

    fun navigateToAddRoom() {
        navigator?.navigateToAddRoomActivity()
    }

}