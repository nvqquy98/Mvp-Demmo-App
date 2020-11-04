package com.odinwar.mvpdemmoapp.data.source

import com.odinwar.mvpdemmoapp.data.model.Photo
import com.odinwar.mvpdemmoapp.data.source.remote.OnFetChDataJsonListener

interface PhotoDataSource {
    interface Remote {
        fun getPhoto(listener: OnFetChDataJsonListener<MutableList<Photo>>)
    }
}
