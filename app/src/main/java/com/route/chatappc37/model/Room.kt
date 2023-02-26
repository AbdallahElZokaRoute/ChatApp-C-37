package com.route.chatappc37.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var categoryId: String? = null
) : Parcelable {

    companion object {
        const val COLLECTION_NAME = "Rooms"
    }
}
