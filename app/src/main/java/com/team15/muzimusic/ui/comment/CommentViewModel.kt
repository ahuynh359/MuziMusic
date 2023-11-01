package com.team15.muzimusic.ui.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.Comment
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.repositories.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentRepository: CommentRepository
) : BaseViewModel() {

    var rootComments = MutableLiveData<List<Comment>>()
    var parentComment = MutableLiveData<Comment>()
    var message: String? = null

    var commentUpdate: Comment? = null

    var sendStatus = MutableLiveData<Boolean?>(null)
    var deleteStatus = MutableLiveData<Boolean?>(null)
    var deleteParentStatus = MutableLiveData<Boolean?>(null)

    fun isUpdateComment(): Boolean = commentUpdate != null

    fun getALlComments(song: Song) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            rootComments.postValue(commentRepository.getAllComments(song.idSong))
        }
        registerEventParentJobFinish()
    }

    fun getCommentAndChildren(comment: Comment) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            parentComment.postValue(commentRepository.getCommentAndChildren(comment.idComment))
        }
        registerEventParentJobFinish()
    }

    fun addComment(song: Song, content: String) {
        viewModelScope.launch {
            val result = commentRepository.addComment(song.idSong, content)
            if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            sendStatus.postValue(result is NetworkResult.Success)
        }
    }

    fun updateComment(song: Song, comment: Comment, content: String) {
        viewModelScope.launch {
            val result = commentRepository.updateComment(song.idSong, comment.idComment, content)
            if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            sendStatus.postValue(result is NetworkResult.Success)
        }
    }

    fun replyComment(song: Song, comment: Comment, content: String) {
        viewModelScope.launch {
            val result = commentRepository.replayComment(song.idSong, comment.idComment, content)
            if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            sendStatus.postValue(result is NetworkResult.Success)
        }
    }

    fun deleteComment(song: Song, comment: Comment) {
        viewModelScope.launch {
            val result = commentRepository.deleteComment(song.idSong, comment.idComment)
            if (result is NetworkResult.Success) {
                message = result.body.message
            } else if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            deleteStatus.postValue(result is NetworkResult.Success)
        }
    }

    fun deleteParentComment(song: Song, comment: Comment) {
        viewModelScope.launch {
            val result = commentRepository.deleteComment(song.idSong, comment.idComment)
            if (result is NetworkResult.Success) {
                message = result.body.message
            } else if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            deleteParentStatus.postValue(result is NetworkResult.Success)
        }
    }

}