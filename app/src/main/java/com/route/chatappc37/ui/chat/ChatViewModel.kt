package com.route.chatappc37.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentChange
import com.route.chatappc37.database.addMessage
import com.route.chatappc37.database.getMessages
import com.route.chatappc37.database.listenForMessagesChanges
import com.route.chatappc37.model.DataUtils
import com.route.chatappc37.model.Message
import com.route.chatappc37.model.Room
import com.route.chatappc37.ui.base.BaseViewModel

class ChatViewModel : BaseViewModel<Navigator>() {
    val messageInput = ObservableField<String>()
    val isScrollToBottom = MutableLiveData<Boolean>(false)
    val messageReceivedLiveData = MutableLiveData<Message>()
    val messagesLiveData = MutableLiveData<MutableList<Message>>()
    var room: Room? = null

    fun getMessagesFromFireStore() {
        getMessages(
            room?.id!!,
            onSuccessListener = {
                messagesLiveData.value = it.toObjects(Message::class.java)
            },
            onFailureListener = {
                messageLiveData.value = it.localizedMessage
            }
        )

    }

    fun listenForChanges() {
        listenForMessagesChanges(room?.id!!, snapShotListener = { snapshots, exception ->
            if (exception != null) {
                messageLiveData.value = exception.localizedMessage
                return@listenForMessagesChanges
            }
            for (dc in snapshots!!.documentChanges) {

                when (dc.type) {
                    DocumentChange.Type.ADDED -> {
                        messageReceivedLiveData.value = dc.document.toObject(Message::class.java)
                    }
//                    DocumentChange.Type.MODIFIED -> Log.d(TAG, "Modified city: ${dc.document.data}")
//                    DocumentChange.Type.REMOVED -> Log.d(TAG, "Removed city: ${dc.document.data}")
                    else -> Unit
                }
            }

        })
    }

    fun sendMessage() {
        if (messageInput.get()
                .isNullOrBlank()
        ) {
            return
        }
        val message = Message(
            content = messageInput.get(),
            senderId = DataUtils.user?.id,
            senderName = DataUtils.user?.firstName,
            roomId = room?.id,
        )
        addMessage(
            message,
            onSuccessListener = {
                messageInput.set("")
                isScrollToBottom.value = true
            }, onFailureListener = {
                messageLiveData.value = it.localizedMessage
            }
        )

    }
}