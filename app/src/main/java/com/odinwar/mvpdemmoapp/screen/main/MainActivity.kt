package com.odinwar.mvpdemmoapp.screen.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.odinwar.mvpdemmoapp.R
import com.odinwar.mvpdemmoapp.data.model.Photo
import com.odinwar.mvpdemmoapp.data.source.PhotoRepository
import com.odinwar.mvpdemmoapp.screen.main.apdater.PhotoAdapter
import com.odinwar.mvpdemmoapp.utils.OnItemRecyclerViewClickListener

class MainActivity : AppCompatActivity(), MainContact.View,
    OnItemRecyclerViewClickListener<Photo> {

    private val photoAdapter: PhotoAdapter by lazy { PhotoAdapter() }
    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        photoRecyclerView = findViewById(R.id.photoRecyclerView)
        initView()
        initData()
    }

    private fun initView() {
        photoRecyclerView.apply {
            setHasFixedSize(true)
            adapter = photoAdapter
            layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        }
        photoAdapter.registerItemRecyclerViewClickListener(this)
    }

    private fun initData() {
        val presenterImpl = MainPresenter(PhotoRepository.instance)
        presenterImpl.setView(this)
        presenterImpl.onStart()
    }

    override fun onGetPhotoSuccess(photo: MutableList<Photo>) {
        photoAdapter.updateData(photo)
    }

    override fun onError(error: Exception?) {
        Toast.makeText(this, error?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClickListener(item: Photo?) {
        Toast.makeText(this, item?.id, Toast.LENGTH_SHORT).show()
    }
}
