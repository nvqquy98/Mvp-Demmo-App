package com.odinwar.mvpdemmoapp.screen.main.apdater

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.odinwar.mvpdemmoapp.R
import com.odinwar.mvpdemmoapp.data.model.Photo
import com.odinwar.mvpdemmoapp.utils.LoadImageFromUrl
import com.odinwar.mvpdemmoapp.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private var photos = mutableListOf<Photo>()
    private var onItemRecyclerViewClickListener: OnItemRecyclerViewClickListener<Photo>? = null

    fun updateData(data: MutableList<Photo>) {
        photos.let {
            it.clear()
            it.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun registerItemRecyclerViewClickListener(onItemRecyclerViewClickListener: OnItemRecyclerViewClickListener<Photo>) {
        this.onItemRecyclerViewClickListener = onItemRecyclerViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.imageViewPicture.setImageDrawable(null)
        holder.itemView.imageViewUser.setImageDrawable(null)
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val listener = onItemRecyclerViewClickListener

        fun bind(photo: Photo) {
            itemView.setOnClickListener(this)
            loadImage(photo.photoUrl, R.id.imageViewUrl)
            loadImage(photo.photoUserUrl, R.id.imageViewUser)
            itemView.textViewDescription.text = photo.description
            itemView.textViewUserName.text = photo.userName
        }

        private fun loadImage(imageUrl: String, id: Int) {
            when (id) {
                R.id.imageViewUrl -> LoadImageFromUrl(itemView.imageViewPicture).executeOnExecutor(
                    AsyncTask.THREAD_POOL_EXECUTOR,
                    imageUrl
                )
                R.id.imageViewUser -> LoadImageFromUrl(itemView.imageViewUser).executeOnExecutor(
                    AsyncTask.THREAD_POOL_EXECUTOR,
                    imageUrl
                )
            }
        }

        override fun onClick(v: View?) {
            listener?.onItemClickListener(photos[adapterPosition])
        }
    }
}
