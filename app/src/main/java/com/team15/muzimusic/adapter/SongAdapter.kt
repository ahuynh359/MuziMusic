package com.team15.muzimusic.adapter


import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.singersToString
import com.team15.muzimusic.databinding.ItemSongBinding

class SongAdapter(
    private val listener: SongClickListener,
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {


    private var songs: List<Song> = arrayListOf()

    class SongViewHolder(val itemBinding: ItemSongBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Song>) {
        songs = data
        notifyDataSetChanged()
    }

    fun getSongs(): List<Song> = songs

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: SongViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val song = songs[position]

        holder.itemBinding.apply {

            tvIndex.text = "${position + 1}"
            if (song.imageFile != null) {
                Picasso.get().load(song.imageFile!!).placeholder(R.drawable.songs)
                    .fit().into(imgSongAvatar)
            } else {
                if (song.image.isNotEmpty()) {
                    Picasso.get().load(song.image).placeholder(R.drawable.songs)
                        .fit().into(imgSongAvatar)
                }
            }


            tvSongName.text = song.name
            tvAccountName.text = song.singersToString()

            if (position < 3) {
                tvIndex.setTextColor(Constants.colorsTopSong[position])
                tvIndex.typeface = Typeface.DEFAULT_BOLD
            }


        }

        holder.itemView.setOnClickListener {
            listener.onSongClick(song)
        }

        holder.itemBinding.songMenuMore.setOnClickListener {
            listener.onOpenMenu(song, position)
        }
    }

    override fun getItemCount(): Int = songs.size
}
