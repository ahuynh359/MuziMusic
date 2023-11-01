package com.team15.muzimusic.ui.splash

import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.common.AppSharedPreferences
import com.team15.muzimusic.common.DataLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appSharedPreferences: AppSharedPreferences
) : BaseViewModel() {

    fun getSavedData() {
        DataLocal.IS_LOGGED_IN = appSharedPreferences.isLoggedIn()
        DataLocal.ACCESS_TOKEN = appSharedPreferences.getAccessToken()
        appSharedPreferences.getUser()?.let {
            DataLocal.myAccount = it
        }
    }

    fun isFirstTime(): Boolean {

        return appSharedPreferences.isFirstTime()

    }

    fun saveFirstTime() {
        appSharedPreferences.saveFirstTime()
    }
}