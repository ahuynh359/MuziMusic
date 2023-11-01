package com.team15.muzimusic.ui.account.changeprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.models.AccountUpdate
import com.team15.muzimusic.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChangeProfileViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    var message: String? = null
    var status = MutableLiveData<Boolean?>(null)

    var avatarFile: File? = null
    var accountName: String = ""

    fun updateAccount(account: AccountUpdate) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            val result = accountRepository.updateAccount(account)
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