package com.team15.muzimusic.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.singersToString
import com.team15.muzimusic.databinding.ItemSongLiteBinding

class SongLiteAdapter(
    private val listener: SongClickListener,
    private val removeSongFromPlaylistListener: RemoveSongFromPlaylistListener? = null
) : RecyclerView.Adapter<SongLiteAdapter.SongViewHolder>() {

    var showRemoveSongFromPlaylist: Boolean = false


    inner class SongViewHolder(val itemBinding: ItemSongLiteBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ItemSongLiteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = differ.currentList[position]

        holder.itemBinding.apply {
            if (song.image.isNotEmpty()) {
                val id = song.image.split("id=")
                Picasso.get().load(
                        "https://drive.google.com/thumbnail?id=${id.get(1)}").fit().into(imgSongAvatar)
            } else {

                Picasso.get().load(R.drawable.songs).fit().into(imgSongAvatar)
            }
            tvSongName.text = song.name
            tvAccountName.text = song.singersToString()

            removeSongFromPlaylist.visibility =
                if (showRemoveSongFromPlaylist) View.VISIBLE
                else View.GONE

            removeSongFromPlaylist.setOnClickListener {
                removeSongFromPlaylistListener?.onRemoveSongFromPlaylist(song, position)
            }
        }

        holder.itemView.setOnClickListener {
            listener.onSongClick(song)
        }

        holder.itemBinding.songMenuMore.setOnClickListener {
            listener.onOpenMenu(song, position)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}