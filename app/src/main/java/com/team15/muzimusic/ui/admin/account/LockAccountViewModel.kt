package com.team15.muzimusic.ui.admin.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LockAccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    var lockAccounts = MutableLiveData<List<Account>>()

    var message: String? = null
    var status = MutableLiveData<Boolean?>(null)

    fun getLocksAccount() {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            lockAccounts.postValue(accountRepository.getLockAccounts())
        }
        registerEventParentJobFinish()
    }

    fun lockAccount(account: Account) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            val result = accountRepository.lockAccount(account.idAccount)
            if (result is NetworkResult.Success) {
                message = result.body.message
            } else if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            status.postValue(result is NetworkResult.Success)
        }
        registerEventParentJobFinish()
    }

    fun unlockAccount(account: Account) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            val result = accountRepository.unlockAccount(account.idAccount)
            if (result is NetworkResult.Success) {
                message = result.body.message
            } else if (result is NetworkResult.Error) {
                message = result.responseError.message
            }
            status.postValue(result is NetworkResult.Success)
        }
        registerEventParentJobFinish()
    }
}