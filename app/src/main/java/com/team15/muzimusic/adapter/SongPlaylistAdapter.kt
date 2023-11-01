package com.team15.muzimusic.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.databinding.ItemSongPlaylistBinding
import java.util.*

class SongPlaylistAdapter(
    private val dragListener: OnStartDragListener,
    private val listener: SongPlaylistListener
) :
    RecyclerView.Adapter<SongPlaylistAdapter.SongViewHolder>(), ItemTouchHelperAdapter {

    var songs: ArrayList<Song> = ArrayList()

    private var currentSong: Song? = null

    fun setCurrentSong(song: Song) {
        val oldSongIndex = songs.indexOfFirst { currentSong?.idSong == it.idSong }
        val newSongIndex = songs.indexOfFirst { song.idSong == it.idSong }

        if (oldSongIndex != -1) notifyItemChanged(oldSongIndex)
        if (newSongIndex != -1) notifyItemChanged(newSongIndex)

        currentSong = song
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<Song>) {
        songs = data
        currentSong = songs.firstOrNull()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ItemSongPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            dragListener
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.bindData(song)
        holder.itemView.setOnClickListener {
            listener.onSongPlaylistClick(song, position)
        }
    }

    override fun getItemCount(): Int = songs.size

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(songs, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)

        listener.onSongPlaylistReorder(songs)

        return true
    }

    override fun onItemDismiss(position: Int) {
    }

    inner class SongViewHolder(
        private val itemBinding: ItemSongPlaylistBinding,
        private val dragListener: OnStartDragListener? = null
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("ClickableViewAccessibility")
        fun bindData(song: Song) {
            itemBinding.apply {
                Picasso.get().load(song.image).fit().into(imgSongAvatar)

                tvSongName.text = song.name
                tvAccountName.text = song.account?.accountName ?: ""

                if (currentSong?.idSong == song.idSong) {
                    imgReorder.visibility = View.INVISIBLE
                } else {
                    root.background = null
                    imgReorder.visibility = View.VISIBLE
                }

                imgReorder.setOnTouchListener { _, motionEvent ->
                    if (motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                        dragListener?.onStartDrag(this@SongViewHolder)

                    }
                    return@setOnTouchListener false
                }
            }
        }
    }
}