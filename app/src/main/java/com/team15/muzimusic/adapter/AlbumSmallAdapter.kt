package com.team15.muzimusic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.data.models.Album
import com.team15.muzimusic.databinding.ItemAlbumSmallBinding

class AlbumSmallAdapter(val listener: AlbumClickListener) :
    RecyclerView.Adapter<AlbumSmallAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(val itemBinding: ItemAlbumSmallBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.idAlbum == newItem.idAlbum
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            ItemAlbumSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = differ.currentList[position]

        holder.itemBinding.apply {
            album.songs?.let {
                if(it.isNotEmpty() && it[0].image.isNotEmpty()){
                    Picasso.get().load(it[0].image).placeholder(R.drawable.songs).fit().into(imgAvatar)
                }
            }
            tvAlbumName.text = album.name
            tvSinger.text = album.account?.accountName
        }

        holder.itemView.setOnClickListener {
            listener.onAlbumClick(album)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}