package com.team15.muzimusic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team15.muzimusic.R
import com.team15.muzimusic.data.models.Comment
import com.team15.muzimusic.databinding.ItemCommentReplyLiteBinding

class CommentReplyLiteAdapter(
    private val parentComment: Comment,
    private val listener: CommentReplyClickListener
) :
    RecyclerView.Adapter<CommentReplyLiteAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(val itemBinding: ItemCommentReplyLiteBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.idComment == newItem.idComment
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        differ.submitList(parentComment.children)

        return CommentViewHolder(
            ItemCommentReplyLiteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = differ.currentList[position]

        holder.itemBinding.apply {
            if (comment.account.avatar.isNotEmpty()) {
                Picasso.get().load(comment.account.avatar).placeholder(R.drawable.ic_user)
                    .fit().into(imgAvatar)
            }
            tvAccountName.text = comment.account.accountName
            tvCommentContent.text = comment.content
        }

        holder.itemView.setOnClickListener {
            listener.onCommentReplyClick(parentComment)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}

interface CommentReplyClickListener {
    fun onCommentReplyClick(comment: Comment)
}