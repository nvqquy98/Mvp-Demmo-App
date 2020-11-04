package com.odinwar.mvpdemmoapp.data.source

import com.odinwar.mvpdemmoapp.data.model.Photo
import com.odinwar.mvpdemmoapp.data.source.remote.OnFetChDataJsonListener
import com.odinwar.mvpdemmoapp.data.source.remote.PhotoRemoteDataSourceImpl

class PhotoRepository(private val remote: PhotoDataSource.Remote) {

    private object Holder {
        val INSTANCE = PhotoRepository(remote = PhotoRemoteDataSourceImpl.instance)
    }

    fun getPhoto(listener: OnFetChDataJsonListener<MutableList<Photo>>) {
        remote.getPhoto(listener)
    }

    companion object {
        val instance: PhotoRepository by lazy { Holder.INSTANCE }
    }
}
