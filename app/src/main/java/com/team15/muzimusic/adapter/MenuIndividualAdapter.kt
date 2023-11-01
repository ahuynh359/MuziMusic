package com.team15.muzimusic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.team15.muzimusic.data.models.MenuClickListener
import com.team15.muzimusic.data.models.MenuIndividual
import com.team15.muzimusic.databinding.ItemMenuIndividualBinding

class MenuIndividualAdapter(private val listener: MenuClickListener) :
    RecyclerView.Adapter<MenuIndividualAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val itemBinding: ItemMenuIndividualBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<MenuIndividual>() {
        override fun areItemsTheSame(oldItem: MenuIndividual, newItem: MenuIndividual): Boolean {
            return oldItem.type == newItem.type
        }

        override fun areContentsTheSame(oldItem: MenuIndividual, newItem: MenuIndividual): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            ItemMenuIndividualBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = differ.currentList[position]

        holder.itemBinding.apply {
            tvMenuName.text = menu.title
            imgMenu.setImageResource(menu.drawableRes)
        }

        holder.itemView.setOnClickListener {
            listener.onMenuClick(menu)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}