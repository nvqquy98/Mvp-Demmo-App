package com.odinwar.mvpdemmoapp.data.model

data class Photo(
    val id: String = "",
    val description: String = "",
    val userName: String = "",
    val photoUrl: String = "",
    val photoUserUrl: String = ""
)

object PhotoEntry {
    const val ID = "id"
    const val PHOTO_URL_JSON_OBJECT = "urls"
    const val PHOTO_URL = "full"
    const val INFORMATION_USER_JSON_OBJECT = "user"
    const val DESCRIPTION = "alt_description"
    const val USER_NAME = "name"
    const val PROFILE_IMAGE_JSON_OBJECT = "profile_image"
    const val PHOTO_USER = "small"
}
