package com.team15.muzimusic.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.common.Helper
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.singersToString
import com.team15.muzimusic.databinding.ItemSongTopBinding

class SongTopAdapter(
    private val listener: SongClickListener
) :
    RecyclerView.Adapter<SongTopAdapter.SongViewHolder>() {

    inner class SongViewHolder(val itemBinding: ItemSongTopBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.idSong == newItem.idSong
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ItemSongTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = differ.currentList[position]

        holder.itemBinding.apply {
            if (song.image.isEmpty()) {
                Picasso.get().load(R.drawable.songs).fit()
                    .into(imgAvatar)
            } else {
                Picasso.get().load(song.image)
                    .placeholder(R.drawable.songs).fit()
                    .into(imgAvatar)
            }

            tvTopNumber.text = "#${position + 1}"
            tvSongName.text = song.name
            tvSinger.text = song.singersToString()
            tvDate.text = Helper.toDateString(song.dateCreated)
        }

        holder.itemView.setOnClickListener {
            listener.onSongClick(song)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}
