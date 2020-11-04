package com.odinwar.mvpdemmoapp.data.source.remote

interface OnFetChDataJsonListener<T> {
    fun onSuccess(data: T)
    fun onError(e: Exception?)
}
