package com.route.chatappc37.ui.addRoom

import androidx.lifecycle.MutableLiveData
import com.route.chatappc37.database.addRoom
import com.route.chatappc37.model.Category
import com.route.chatappc37.model.Room
import com.route.chatappc37.ui.base.BaseViewModel

class AddRoomViewModel : BaseViewModel<Navigator>() {

    val roomName = MutableLiveData<String>()
    val roomNameError = MutableLiveData<String>()
    val roomDescription = MutableLiveData<String>()
    val roomDescriptionError = MutableLiveData<String>()
    val categoriesList = Category.getCategoryList()
    var selectedCategory = categoriesList[0]
    var finishActivity = MutableLiveData<Boolean>()
    fun addRoomToFirestore() {
        showProgressBar.value = true
        val room = Room(
            name = roomName.value,
            description = roomDescription.value,
            categoryId = selectedCategory.id
        )
        addRoom(room,
            onSuccessListener = {
                showProgressBar.value = false
                finishActivity.value = true
            }, onFailureListener = {
                showProgressBar.value = false
                messageLiveData.value = it.message
            })

    }
}