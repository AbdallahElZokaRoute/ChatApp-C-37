package com.route.chatappc37.model

import com.route.chatappc37.R

data class Category(
    var id: String? = null,
    var imageResId: Int? = null,
    var name: String? = null
) {
    companion object {
        const val MUSIC = "music"
        const val SPORTS = "sports"
        const val MOVIES = "movies"

        fun fromId(id: String): Category {
            return when (id) {
                MUSIC -> Category(MUSIC, R.drawable.music, "Music")
                SPORTS -> Category(SPORTS, R.drawable.sports, "Sports")
                MOVIES -> Category(MOVIES, R.drawable.movies, "Movies")
                else -> Category(SPORTS, R.drawable.sports, "Sports")
            }

        }

        fun getCategoryList(): List<Category> {
            return listOf(
                fromId(MUSIC),
                fromId(SPORTS),
                fromId(MOVIES)

            )

        }


    }

}
