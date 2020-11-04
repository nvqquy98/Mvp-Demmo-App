package com.odinwar.mvpdemmoapp.utils

interface BasePresenter<T> {
    fun onStart()
    fun onStop()
    fun setView(view: T?)
}
