package com.team15.muzimusic.data.repositories

import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.data.models.Comment
import com.team15.muzimusic.data.models.MessageJson
import com.team15.muzimusic.data.models.toListComment
import com.team15.muzimusic.data.services.comment.CommentRemoteService
import com.team15.muzimusic.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val remoteService: CommentRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getAllComments(idSong: Int): List<Comment> = withContext(dispatcher) {
        val list = remoteService.getAllComments(idSong)
        list?.toListComment() ?: emptyList()
    }

    suspend fun getCommentAndChildren(idCmt: Int): Comment? = withContext(dispatcher) {
        val result = remoteService.getCommentAndChildren(idCmt)
        result?.toComment()
    }

    suspend fun addComment(idSong: Int, content: String): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.addComment(idSong, CommentModal(content))
        }
    }

    suspend fun updateComment(
        idSong: Int,
        idCmt: Int,
        content: String
    ): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.updateComment(idSong, idCmt, CommentModal(content))
        }
    }

    suspend fun deleteComment(idSong: Int, idCmt: Int): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.deleteComment(idSong, idCmt)
        }
    }

    suspend fun replayComment(
        idSong: Int,
        idCmtParent: Int,
        content: String
    ): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.replyComment(idSong, idCmtParent, CommentModal(content))
        }
    }

}

data class CommentModal(val content: String)