package com.odinwar.mvpdemmoapp.screen.main.apdater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.odinwar.mvpdemmoapp.R
import com.odinwar.mvpdemmoapp.data.model.Photo
import com.odinwar.mvpdemmoapp.utils.OnItemRecyclerViewClickListener
import com.squareup.picasso.Picasso

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
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val imageViewURL = itemView.findViewById<ImageView>(R.id.imageViewUrl)
        private val imageViewUser = itemView.findViewById<ImageView>(R.id.imageViewUser)
        private val textViewDescription = itemView.findViewById<TextView>(R.id.textViewDescription)
        private val textViewUserEmail = itemView.findViewById<TextView>(R.id.textViewUserName)
        private var listener = onItemRecyclerViewClickListener
        fun bind(photo: Photo) {
            itemView.setOnClickListener(this)
            Picasso.get().load(photo.photoUrl).resize(145, 145).into(imageViewURL)
            Picasso.get().load(photo.photoUserUrl).resize(48, 48).into(imageViewUser)
            textViewDescription.text = photo.description
            textViewUserEmail.text = photo.userName
        }

        override fun onClick(v: View?) {
            listener?.onItemClickListener(photos[adapterPosition])
        }
    }
}
