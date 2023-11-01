package com.team15.muzimusic.data.repositories

import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.data.models.*
import com.team15.muzimusic.data.services.account.AccountRemoteService
import com.team15.muzimusic.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountRemoteService: AccountRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun login(modal: LoginModal): NetworkResult<LoginResponseJson> {
        return withContext(dispatcher) {
            accountRemoteService.login(modal)
        }
    }

    suspend fun signup(modal: SignupModal): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.signup(modal)
        }
    }

    suspend fun sendAccountDevice(deviceToken: String): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.sendAccountDevice(deviceToken)
        }
    }

    suspend fun forgetEmail(modal: ForgetEmailModal): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.forgetEmail(modal)
        }
    }

    suspend fun forgetCode(modal: ForgetCodeModal): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.forgetCode(modal)
        }
    }

    suspend fun forgetChangePassword(modal: ForgetChangeModal): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.forgetChangePassword(modal)
        }
    }

    suspend fun updateAccount(account: AccountUpdate): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.updateAccount(account)
        }
    }

    suspend fun followAccount(idAccount: Int): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.followAccount(idAccount)
        }
    }

    suspend fun unFollowAccount(idAccount: Int): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.unFollowAccount(idAccount)
        }
    }

    suspend fun getAccount(idAccount: Int): Account? = withContext(dispatcher) {
        accountRemoteService.getAccount(idAccount)
    }

    suspend fun searchAccount(keyword: String): List<Account> = withContext(dispatcher) {
        accountRemoteService.searchAccount(keyword)
    }

    suspend fun getSongsOfAccount(idAccount: Int): List<Song> = withContext(dispatcher) {
        accountRemoteService.getSongsOfAccount(idAccount)
    }

    suspend fun getMySongs(): List<Song> = withContext(dispatcher) {
        accountRemoteService.getMySongs()
    }

    suspend fun getFollowers(idAccount: Int): List<Account> = withContext(dispatcher) {
        accountRemoteService.getFollowers(idAccount)
    }

    suspend fun getFollowings(idAccount: Int): List<Account> = withContext(dispatcher) {
        accountRemoteService.getFollowings(idAccount)
    }

    suspend fun getTopAccounts(): List<Account> = withContext(dispatcher) {
        accountRemoteService.getTopAccounts()
    }

    suspend fun getAlbumsOfAccount(idAccount: Int): List<Album> = withContext(dispatcher) {
        accountRemoteService.getAlbumsOfAccount(idAccount)
    }

    suspend fun getPlaylistsOfAccount(idAccount: Int): List<Playlist> = withContext(dispatcher) {
        accountRemoteService.getPlaylistsOfAccount(idAccount)
    }

    suspend fun changePassword(modal: ChangePasswordModal): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.changePassword(modal)
        }
    }


    suspend fun getLockAccounts(): List<Account> = withContext(dispatcher) {
        accountRemoteService.getLockAccounts()
    }


    suspend fun lockAccount(idAccount: Int): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.lockAccount(idAccount)
        }
    }

    suspend fun unlockAccount(idAccount: Int): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            accountRemoteService.unlockAccount(idAccount)
        }
    }
}