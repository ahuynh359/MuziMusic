package com.team15.muzimusic.activities

import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.data.models.Comment
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.repositories.AccountRepository
import com.team15.muzimusic.data.repositories.CommentRepository
import com.team15.muzimusic.data.repositories.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TricksViewModel @Inject constructor(
    private val songRepository: SongRepository,
    private val commentRepository: CommentRepository,
    private val accountRepository: AccountRepository,
) : BaseViewModel() {

    suspend fun getSong(idSong: Int): Song? {
        return songRepository.getSong(idSong)
    }

    suspend fun getParentComment(idCommentParent: Int): Comment? {
        return commentRepository.getCommentAndChildren(idCommentParent)
    }

    suspend fun getAccount(idAccount: Int): Account? {
        return accountRepository.getAccount(idAccount)
    }
}