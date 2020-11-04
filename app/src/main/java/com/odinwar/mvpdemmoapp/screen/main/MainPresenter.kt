package com.odinwar.mvpdemmoapp.screen.main

import com.odinwar.mvpdemmoapp.data.model.Photo
import com.odinwar.mvpdemmoapp.data.source.PhotoRepository
import com.odinwar.mvpdemmoapp.data.source.remote.OnFetChDataJsonListener

class MainPresenter(
    private val repository: PhotoRepository?
) : MainContact.Presenter {

    private var view: MainContact.View? = null

    override fun getPhoto() {
        repository?.getPhoto(object : OnFetChDataJsonListener<MutableList<Photo>> {
            override fun onSuccess(data: MutableList<Photo>) {
                view?.onGetPhotoSuccess(data)
            }

            override fun onError(e: Exception?) {
                view?.onError(e)
            }
        })
    }

    override fun onStart() {
        getPhoto()
    }

    override fun onStop() {}

    override fun setView(view: MainContact.View?) {
        this.view = view
    }
}
