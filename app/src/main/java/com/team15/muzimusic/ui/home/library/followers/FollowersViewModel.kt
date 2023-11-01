package com.team15.muzimusic.ui.home.library.followers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    var followers = MutableLiveData<List<Account>>()

    fun getFollowers(idAccount: Int) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            followers.postValue(accountRepository.getFollowers(idAccount))
        }
        registerEventParentJobFinish()
    }
}