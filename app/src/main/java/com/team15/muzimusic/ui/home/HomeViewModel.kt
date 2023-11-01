package com.team15.muzimusic.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.common.AppSharedPreferences
import com.team15.muzimusic.common.DataLocal
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.repositories.AccountRepository
import com.team15.muzimusic.data.repositories.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
    private val accountRepository: AccountRepository,
    private val appSharedPreferences: AppSharedPreferences
) : BaseViewModel() {

    var song: MutableLiveData<Song?> = MutableLiveData()
    var isPlaying: MutableLiveData<Boolean> = MutableLiveData(false)

    var numUnreadNotification = MutableLiveData(0)

    init {
        countUnreadNotification()
        getSettings()
    }

    private fun getSettings(){
        viewModelScope.launch {
            DataLocal.IS_SHUFFLE = appSharedPreferences.isShuffle()
            DataLocal.IS_REPEAT = appSharedPreferences.isRepeat()
        }
    }

    fun countUnreadNotification() {
        viewModelScope.launch {
            numUnreadNotification.postValue(notificationRepository.countUnreadNotification())
        }
    }

    fun sendAccountDevice(deviceToken: String) {
        viewModelScope.launch {
            accountRepository.sendAccountDevice(deviceToken)
        }
    }

}