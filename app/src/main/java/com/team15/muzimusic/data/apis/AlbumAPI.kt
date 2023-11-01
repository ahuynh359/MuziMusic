package com.team15.muzimusic.data.apis

import com.team15.muzimusic.common.Config.ApiVersion
import com.team15.muzimusic.data.models.ListAlbumJson
import com.team15.muzimusic.data.models.ListSongJson
import com.team15.muzimusic.data.models.MessageJson
import com.team15.muzimusic.data.services.album.AlbumModal
import retrofit2.Response
import retrofit2.http.*

interface AlbumAPI {

    @POST("$ApiVersion/album")
    suspend fun addAlbum(@Body modal: AlbumModal): Response<MessageJson>

    @PUT("$ApiVersion/album/{idAlbum}")
    suspend fun updateAlbum(
        @Path("idAlbum") idAlbum: Int,
        @Body modal: AlbumModal,
    ): Response<MessageJson>

    @DELETE("$ApiVersion/album/{idAlbum}")
    suspend fun deleteAlbum(@Path("idAlbum") idAlbum: Int): Response<MessageJson>

    @GET("$ApiVersion/album/all")
    suspend fun getAllMyAlbum(): Response<ListAlbumJson>

    @GET("$ApiVersion/album/{idAlbum}/songs")
    suspend fun getSongsOfAlbum(
        @Path("idAlbum") idType: Int
    ): Response<ListSongJson>
}