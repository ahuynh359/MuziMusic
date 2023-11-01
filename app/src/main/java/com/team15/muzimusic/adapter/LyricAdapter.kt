package com.team15.muzimusic.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.team15.muzimusic.R
import com.team15.muzimusic.data.models.LineLyric
import com.team15.muzimusic.databinding.ItemLyricBinding
import java.util.ArrayList

class LyricAdapter(
    private var lyrics: ArrayList<LineLyric>,
    val context: Context,
    val listener: LyricsClickListener
) :
    RecyclerView.Adapter<LyricAdapter.LyricViewHolder>() {

    private var current: Int = -1;

    fun setData(lyrics: ArrayList<LineLyric>) {
        this.lyrics.clear()
        this.lyrics.addAll(lyrics)
        notifyDataSetChanged()
    }

    fun currentLine(position: Int) {
        if (position != current && position >= 0 && position < itemCount) {
            notifyItemChanged(current)
            current = position
            notifyItemChanged(current)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LyricViewHolder {
        return LyricViewHolder(
            ItemLyricBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LyricViewHolder, position: Int) {
        val line = lyrics[position]

        holder.itemBinding.apply {
            tvLineLyric.text = line.text

            if (current == position) {
                tvLineLyric.setTextColor(ContextCompat.getColor(context, R.color.razzle_dazzle_rose))
                tvLineLyric.typeface = Typeface.DEFAULT_BOLD
            } else {
                tvLineLyric.setTextColor(ContextCompat.getColor(context, R.color.white))
                tvLineLyric.typeface = Typeface.DEFAULT
            }
        }

        holder.itemView.setOnClickListener {
            listener.onLineLyricClick(line)
        }
    }

    override fun getItemCount(): Int = lyrics.size

    inner class LyricViewHolder(val itemBinding: ItemLyricBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}