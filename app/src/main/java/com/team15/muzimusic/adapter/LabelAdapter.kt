package com.team15.muzimusic.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.singersToString
import com.team15.muzimusic.databinding.ItemLabelBinding

class LabelAdapter(
     private val listener: SongClickListener,
) : RecyclerView.Adapter<LabelAdapter.SongViewHolder>() {


    inner class SongViewHolder(val itemBinding: ItemLabelBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback =
        object : DiffUtil.ItemCallback<com.team15.muzimusic.data.models.Result>() {
            override fun areItemsTheSame(
                oldItem: com.team15.muzimusic.data.models.Result,
                newItem: com.team15.muzimusic.data.models.Result
            ): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: com.team15.muzimusic.data.models.Result,
                newItem: com.team15.muzimusic.data.models.Result
            ): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ItemLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = differ.currentList[position]

        holder.itemBinding.apply {
            /*if (song.image.isNotEmpty()) {
                Picasso.get().load(song.image).fit().into(imgSongAvatar)
            } else {
                Picasso.get().load(R.drawable.songs).fit().into(imgSongAvatar)
            }*/
            tvSongName.text = song.label
            Log.d("anh",song.label)
            //tvAccountName.text = song.singersToString()


        }

        /* holder.itemView.setOnClickListener {
             listener.onSongClick(song)
         }

         holder.itemBinding.songMenuMore.setOnClickListener {
             listener.onOpenMenu(song, position)
         }*/
    }

    override fun getItemCount(): Int = differ.currentList.size
}