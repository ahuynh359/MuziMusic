package com.team15.muzimusic.adapter

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.singersToString
import com.team15.muzimusic.databinding.ItemSongStatisticBinding

class SongStatisticAdapter(
    private val listener: SongClickListener,
) : RecyclerView.Adapter<SongStatisticAdapter.SongViewHolder>() {

    private var songs: List<Song> = arrayListOf()

    class SongViewHolder(val itemBinding: ItemSongStatisticBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(song: Song) {
            itemBinding.apply {

                tvIndex.text = "${bindingAdapterPosition + 1}"
                if (song.imageFile != null) {
                    Picasso.get().load(song.imageFile!!).placeholder(R.drawable.songs)
                        .fit().into(imvSongAvatar)
                } else {
                    if (song.image.isNotEmpty()) {
                        Picasso.get().load(song.image).placeholder(R.drawable.songs)
                            .fit().into(imvSongAvatar)
                    }
                }


                tvSongName.text = song.name
                tvAccountName.text = song.singersToString()
                tvTotalListen.text = song.listen.toString()
                tvTotalHeart.text = song.like.toString()


                if (bindingAdapterPosition < 3) {
                    tvIndex.setTextColor(Constants.colorsTopSong[bindingAdapterPosition])
                    tvIndex.typeface = Typeface.DEFAULT_BOLD
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Song>) {
        songs = data
        notifyDataSetChanged()
    }

    fun getSongs(): List<Song> = songs

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ItemSongStatisticBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: SongViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val song = songs[position]
        holder.bindData(song)
    }

    override fun getItemCount(): Int = songs.size
}
