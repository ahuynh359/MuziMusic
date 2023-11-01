package com.team15.muzimusic.data.services.comment

import com.team15.muzimusic.base.network.BaseRemoteService
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.data.apis.CommentAPI
import com.team15.muzimusic.data.models.CommentJson
import com.team15.muzimusic.data.models.CommentParentJson
import com.team15.muzimusic.data.models.MessageJson
import com.team15.muzimusic.data.repositories.CommentModal
import javax.inject.Inject

class CommentRemoteService @Inject constructor(
    private val commentAPI: CommentAPI
) : BaseRemoteService() {

    suspend fun getAllComments(idSong: Int): List<CommentJson>? {
        val result = callApi { commentAPI.getAllComments(idSong) }
        return if (result is NetworkResult.Success)
            result.body.data
        else null
    }

    suspend fun getCommentAndChildren(idCmt: Int): CommentParentJson? {
        val result = callApi { commentAPI.getCommentAndChildren(idCmt) }
        return if (result is NetworkResult.Success)
            result.body.data
        else null
    }

    suspend fun addComment(idSong: Int, modal: CommentModal): NetworkResult<MessageJson> {
        return callApi { commentAPI.addComment(idSong, modal) }
    }

    suspend fun updateComment(
        idSong: Int,
        idCmt: Int,
        modal: CommentModal
    ): NetworkResult<MessageJson> {
        return callApi { commentAPI.updateComment(idSong, idCmt, modal) }
    }

    suspend fun deleteComment(
        idSong: Int,
        idCmt: Int,
    ): NetworkResult<MessageJson> {
        return callApi { commentAPI.deleteComment(idSong, idCmt) }
    }

    suspend fun replyComment(
        idSong: Int,
        idCmtParent: Int,
        modal: CommentModal
    ): NetworkResult<MessageJson> {
        return callApi { commentAPI.addReplyComment(idSong, idCmtParent, modal) }
    }
}