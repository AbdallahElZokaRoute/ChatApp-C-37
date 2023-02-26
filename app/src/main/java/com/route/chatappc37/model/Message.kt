package com.route.chatappc37.model

import java.util.*

data class Message(
    var id: String? = null,
    var content: String? = null,
    var senderId: String? = null,
    var time: Long? = Date().time,
    var roomId: String? = null

) {
    companion object {
        const val COLLECTION_NAME = "Messages"
    }

}
