package com.team15.muzimusic.ui.account.changepassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.ChangePasswordModal
import com.team15.muzimusic.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    var message: String? = null
    var status = MutableLiveData<Boolean?>(null)

    fun changePassword(modal: ChangePasswordModal) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            val result = accountRepository.changePassword(modal)
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