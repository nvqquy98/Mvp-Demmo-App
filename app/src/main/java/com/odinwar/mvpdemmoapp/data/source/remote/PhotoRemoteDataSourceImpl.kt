package com.odinwar.mvpdemmoapp.data.source.remote

import com.odinwar.mvpdemmoapp.data.model.ConstantsModel
import com.odinwar.mvpdemmoapp.data.model.Photo
import com.odinwar.mvpdemmoapp.data.source.PhotoDataSource
import com.odinwar.mvpdemmoapp.data.source.remote.fetchjson.GetJsonFromUrl
import com.odinwar.mvpdemmoapp.utils.Constants

class PhotoRemoteDataSourceImpl : PhotoDataSource.Remote {

    private val url = Constants.BASE_URL + Constants.BASE_API_KEY

    private object Holder {
        val INSTANCE = PhotoRemoteDataSourceImpl()
    }

    override fun getPhoto(listener: OnFetChDataJsonListener<MutableList<Photo>>) {
        GetJsonFromUrl(listener, ConstantsModel.PHOTO).execute(url)
    }

    companion object {
        val instance: PhotoRemoteDataSourceImpl by lazy { Holder.INSTANCE }
    }
}
