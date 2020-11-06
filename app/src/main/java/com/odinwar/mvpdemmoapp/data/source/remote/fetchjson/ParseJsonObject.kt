package com.odinwar.mvpdemmoapp.data.source.remote.fetchjson

import com.odinwar.mvpdemmoapp.data.model.Photo
import com.odinwar.mvpdemmoapp.data.model.PhotoEntry
import org.json.JSONObject

class ParseJsonObject {

    fun pareJsonToPhoto(jsonObject: JSONObject): Photo? {
        try {
            val userJsonObject = jsonObject.getJSONObject(PhotoEntry.INFORMATION_USER_JSON_OBJECT)
            return Photo(
                id = jsonObject.getString(PhotoEntry.ID),
                description = jsonObject.getString(PhotoEntry.DESCRIPTION),
                userName = userJsonObject.getString(PhotoEntry.USER_NAME),
                photoUrl = jsonObject.getJSONObject(PhotoEntry.PHOTO_URL_JSON_OBJECT)
                    .getString(PhotoEntry.PHOTO_URL),
                photoUserUrl = userJsonObject.getJSONObject(PhotoEntry.PROFILE_IMAGE_JSON_OBJECT)
                    .getString(PhotoEntry.PHOTO_USER)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
