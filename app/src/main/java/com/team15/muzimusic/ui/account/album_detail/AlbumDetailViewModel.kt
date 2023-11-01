package com.team15.muzimusic.ui.account.album_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.repositories.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : BaseViewModel() {

    var songs = MutableLiveData<List<Song>>()

    fun getSongsOfAlbum(idAlbum: Int) {
        isLoading.postValue(true)
        viewModelScope.launch {
            songs.postValue(albumRepository.getSongsOfAlbum(idAlbum))
        }
        registerEventParentJobFinish()
    }
}