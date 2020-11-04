package com.odinwar.mvpdemmoapp.screen.main

import com.odinwar.mvpdemmoapp.data.model.Photo
import com.odinwar.mvpdemmoapp.utils.BasePresenter

interface MainContact {
    interface View {
        fun onGetPhotoSuccess(photo: MutableList<Photo>)
        fun onError(error: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getPhoto()
    }
}
