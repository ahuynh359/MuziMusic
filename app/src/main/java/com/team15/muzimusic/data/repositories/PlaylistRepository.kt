package com.team15.muzimusic.data.repositories

import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.data.models.MessageJson
import com.team15.muzimusic.data.models.Playlist
import com.team15.muzimusic.data.models.toListPlaylist
import com.team15.muzimusic.data.services.playlist.PlaylistModal
import com.team15.muzimusic.data.services.playlist.PlaylistRemoteService
import com.team15.muzimusic.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val remoteService: PlaylistRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun addPlaylist(modal: PlaylistModal): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.addPlaylist(modal)
        }
    }

    suspend fun updatePlaylist(idPlaylist: Int, modal: PlaylistModal): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.updatePlaylist(idPlaylist, modal)
        }
    }

    suspend fun deletePlaylist(idPlaylist: Int): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.deletePlaylist(idPlaylist)
        }
    }

    suspend fun addSongToPlaylist(idPlaylist: Int, idSong: Int): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.addSongToPlaylist(idPlaylist, idSong)
        }
    }

    suspend fun deleteSongFromPlaylist(idPlaylist: Int, idSong: Int): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.deleteSongFromPlaylist(idPlaylist, idSong)
        }
    }

    suspend fun getMyPlaylists(): List<Playlist> = withContext(dispatcher) {
        val list = remoteService.getMyPlaylists()
        list?.toListPlaylist() ?: emptyList()
    }

    suspend fun getTopPlaylists(): List<Playlist> = withContext(dispatcher) {
        val list = remoteService.getTopPlaylists()
        list?.toListPlaylist() ?: emptyList()
    }
}