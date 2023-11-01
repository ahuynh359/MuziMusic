package com.team15.muzimusic.ui.menubottom.playlists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.Playlist
import com.team15.muzimusic.data.repositories.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongTopPlaylistViewModel @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : BaseViewModel() {

    var message: String? = null
    var playlists = MutableLiveData<List<Playlist>>()
    var addSongToPlaylistStatus = MutableLiveData<Boolean?>(null)

    fun getMyPlaylists() {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            playlists.postValue(playlistRepository.getMyPlaylists())
        }
        registerEventParentJobFinish()
    }

    fun addSongToPlaylist(idPlaylist: Int, idSong: Int) {
        viewModelScope.launch {
            val result = playlistRepository.addSongToPlaylist(idPlaylist, idSong)
            if (result is NetworkResult.Success) {
                message = result.body.message
            } else if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            addSongToPlaylistStatus.postValue(result is NetworkResult.Success)
        }
    }
}