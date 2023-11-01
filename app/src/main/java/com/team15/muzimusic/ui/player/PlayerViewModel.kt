package com.team15.muzimusic.ui.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.common.AppSharedPreferences
import com.team15.muzimusic.common.DataLocal
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.repositories.AccountRepository
import com.team15.muzimusic.data.repositories.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val songRepository: SongRepository,
    private val accountRepository: AccountRepository,
    private val appSharedPreferences: AppSharedPreferences
) : BaseViewModel() {

    var audioSessionId = MutableLiveData(0)

    var isClear: Boolean = false

    var isPlaying: MutableLiveData<Boolean> = MutableLiveData(false)

    var song: MutableLiveData<Song> = MutableLiveData()

    var songList: MutableLiveData<ArrayList<Song>> = MutableLiveData()

    var songListOrigin: MutableLiveData<ArrayList<Song>> = MutableLiveData()

    var currentSongTime: MutableLiveData<Int> = MutableLiveData(0)

    var isUserTouchedSlider = false

    var currentRotate = 0f

    var message: String? = null
    var loveResponseStatus = MutableLiveData<Boolean?>(null)

    // Recommend Song
    var recommendSongs = MutableLiveData<List<Song>>()

    var isShuffle = MutableLiveData(false)
    var isRepeat = MutableLiveData(false)

    fun setShuffle(value: Boolean) {
        viewModelScope.launch {
            appSharedPreferences.setShuffle(value)
            isShuffle.postValue(value)
            DataLocal.IS_SHUFFLE = value
        }
    }

    fun getShuffle() {
        viewModelScope.launch {
            isShuffle.postValue(appSharedPreferences.isShuffle())
        }
    }

    fun setRepeat(value: Boolean) {
        viewModelScope.launch {
            appSharedPreferences.setRepeat(value)
            isRepeat.postValue(value)
            DataLocal.IS_REPEAT = value
        }
    }

    fun getRepeat() {
        viewModelScope.launch {
            isRepeat.postValue(appSharedPreferences.isRepeat())
        }
    }

    fun getRecommendSong() {
        song.value?.let {
            it.account?.let {
                viewModelScope.launch {
                    val list = accountRepository.getSongsOfAccount(it.idAccount)
                    recommendSongs.postValue(list)
                }
            }
        }
    }

    fun listen(song: Song) {
        viewModelScope.launch {
            songRepository.listen(song.idSong)
        }
    }

    fun getSong(s: Song) {
        viewModelScope.launch {
            val refreshSong = songRepository.getSong(s.idSong)
            refreshSong?.let { s ->
                song.postValue(s)
            }
        }
    }

    fun loveSong(song: Song) {
        viewModelScope.launch {
            val result = songRepository.loveSong(song.idSong)
            if (result is NetworkResult.Success) {
                message = result.body.message
            } else if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            loveResponseStatus.postValue(result is NetworkResult.Success)
        }
    }

    fun unLoveSong(song: Song) {
        viewModelScope.launch {
            val result = songRepository.unLoveSong(song.idSong)
            if (result is NetworkResult.Success) {
                message = result.body.message
            } else if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            loveResponseStatus.postValue(result is NetworkResult.Success)
        }
    }

}