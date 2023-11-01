package com.team15.muzimusic.ui.home.library.mysongs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MySongsViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    var mySongs = MutableLiveData<List<Song>>()

    fun fetchData() {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            mySongs.postValue(accountRepository.getMySongs())
        }
        registerEventParentJobFinish()
    }

}