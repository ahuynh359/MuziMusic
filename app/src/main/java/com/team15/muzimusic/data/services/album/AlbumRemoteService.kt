package com.team15.muzimusic.data.services.album

import com.squareup.moshi.Json
import com.team15.muzimusic.base.network.BaseRemoteService
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.data.apis.AlbumAPI
import com.team15.muzimusic.data.models.ListAlbumJson
import com.team15.muzimusic.data.models.MessageJson
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.toListSong
import javax.inject.Inject

class AlbumRemoteService @Inject constructor(
    private val albumAPI: AlbumAPI
) : BaseRemoteService() {

    suspend fun addAlbum(modal: AlbumModal): NetworkResult<MessageJson> {
        return callApi { albumAPI.addAlbum(modal) }
    }

    suspend fun updateAlbum(idAlbum: Int, modal: AlbumModal): NetworkResult<MessageJson> {
        return callApi { albumAPI.updateAlbum(idAlbum, modal) }
    }

    suspend fun deleteAlbum(idAlbum: Int): NetworkResult<MessageJson> {
        return callApi { albumAPI.deleteAlbum(idAlbum) }
    }

    suspend fun getAllMyAlbum(): NetworkResult<ListAlbumJson> {
        return callApi { albumAPI.getAllMyAlbum() }
    }

    suspend fun getSongsOfAlbum(idAlbum: Int): List<Song> {
        val result = callApi { albumAPI.getSongsOfAlbum(idAlbum) }
        return if (result is NetworkResult.Success) {
            result.body.data.toListSong()
        } else {
            emptyList()
        }
    }
}

data class AlbumModal(
    @Json(name = "name_album") val albumName: String
)