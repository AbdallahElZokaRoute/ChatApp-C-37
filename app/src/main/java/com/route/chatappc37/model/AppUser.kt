package com.route.chatappc37.model

data class AppUser(
    var id: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
){
    companion object{
        val COLLECTION_NAME = "Users"


    }

}