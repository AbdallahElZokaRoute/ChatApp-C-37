package com.route.chatappc37.ui.chat

import androidx.lifecycle.MutableLiveData
import com.route.chatappc37.ui.base.BaseViewModel

class ChatViewModel : BaseViewModel<Navigator>() {
    val messageInput = MutableLiveData<String>()

    fun receiveMessagesFromFireStore() {

    }

    fun sendMessage() {


    }
}