package com.team15.muzimusic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.databinding.ItemAccountBinding

class AccountLockAdapter(val listener: AccountLockListener) :
    RecyclerView.Adapter<AccountLockAdapter.AccountViewHolder>() {

    inner class AccountViewHolder(val itemBinding: ItemAccountBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Account>() {
        override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem.idAccount == newItem.idAccount
        }

        override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = differ.currentList[position]

        holder.itemBinding.apply {
            if (account.avatar.isNotEmpty())
                Picasso.get().load(account.avatar).fit()
                    .into(imvAvatar)
            tvAccountName.text = account.accountName
            tvFollowers.text = "${account.totalFollowers}"

            btnNext.setImageResource(R.drawable.ic_unlock)
            btnNext.setOnClickListener {
                listener.onAccountUnlock(account)
            }
        }

        holder.itemView.setOnClickListener {
            listener.onAccountClick(account)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}

interface AccountLockListener {
    fun onAccountClick(account: Account)
    fun onAccountUnlock(account: Account)
}