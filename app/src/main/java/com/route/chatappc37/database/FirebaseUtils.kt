package com.route.chatappc37.database

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import com.route.chatappc37.model.AppUser
import com.route.chatappc37.model.Message
import com.route.chatappc37.model.Room

fun getCollectionRef(collectionName: String): CollectionReference {
    return FirebaseFirestore.getInstance()
        .collection(collectionName)

}

fun addUser(
    uid: String?,
    user: AppUser,
    onSuccessListener: OnSuccessListener<Void>,
    onFailureListener: OnFailureListener
) {
    getCollectionRef(AppUser.COLLECTION_NAME)
        .document(uid!!)
        .set(user)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun getUser(
    uid: String,
    onSuccessListener: OnSuccessListener<DocumentSnapshot>,
    onFailureListener: OnFailureListener
) {
    getCollectionRef(AppUser.COLLECTION_NAME)
        .document(uid)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun addRoom(
    room: Room,
    onSuccessListener: OnSuccessListener<Void>,
    onFailureListener: OnFailureListener
) {
    val documentRef = getCollectionRef(Room.COLLECTION_NAME)
        .document()
    room.id = documentRef.id
    documentRef.set(room)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun getRooms(
    onSuccessListener: OnSuccessListener<QuerySnapshot>,
    onFailureListener: OnFailureListener
) {
    getCollectionRef(Room.COLLECTION_NAME)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun addMessage(
    message: Message,
    onSuccessListener: OnSuccessListener<Void>,
    onFailureListener: OnFailureListener
) {
    val doc = getCollectionRef(Room.COLLECTION_NAME)
        .document(message.roomId!!)
        .collection(Message.COLLECTION_NAME)
        .document()
    message.id = doc.id
    doc.set(message)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun getMessages(
    roomId: String,
    onSuccessListener: OnSuccessListener<QuerySnapshot>,
    onFailureListener: OnFailureListener
) {
    getCollectionRef(Room.COLLECTION_NAME)
        .document(roomId)
        .collection(Message.COLLECTION_NAME)
        .orderBy("time", Query.Direction.ASCENDING)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}

fun listenForMessagesChanges(
    roomId: String,
    snapShotListener: EventListener<QuerySnapshot>
) {
    getCollectionRef(Room.COLLECTION_NAME)
        .document(roomId)
        .collection(Message.COLLECTION_NAME)
        .addSnapshotListener(snapShotListener)

}
