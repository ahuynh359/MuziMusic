package com.team15.muzimusic.ui.home.library.followings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingsViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    var followings = MutableLiveData<List<Account>>()

    fun getFollowings(idAccount: Int) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            followings.postValue(accountRepository.getFollowings(idAccount))
        }
        registerEventParentJobFinish()
    }
}