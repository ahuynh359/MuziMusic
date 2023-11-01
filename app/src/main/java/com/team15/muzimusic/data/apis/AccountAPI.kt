package com.team15.muzimusic.data.apis

import com.team15.muzimusic.base.network.NetworkHelper
import com.team15.muzimusic.common.Config.ApiVersion
import com.team15.muzimusic.data.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AccountAPI {

    @POST("$ApiVersion/account/login")
    suspend fun login(@Body modal: LoginModal): Response<LoginResponseJson>

    @POST("$ApiVersion/account/")
    suspend fun signup(@Body modal: SignupModal): Response<MessageJson>

    @PUT("$ApiVersion/account/change/device")
    suspend fun sendAccountDevice(@Body modal: DeviceTokenModal): Response<MessageJson>

    @POST("$ApiVersion/account/forget/password")
    suspend fun forgetEmail(@Body modal: ForgetEmailModal): Response<MessageJson>

    @POST("$ApiVersion/account/forget/verify")
    suspend fun forgetCode(@Body modal: ForgetCodeModal): Response<MessageJson>

    @POST("$ApiVersion/account/forget/change")
    suspend fun forgetChangePassword(@Body modal: ForgetChangeModal): Response<MessageJson>

    @Multipart
    @PUT("$ApiVersion/account/change/information")
    suspend fun updateAccount(
        @Part img: MultipartBody.Part,
        @Part("account_name") name: RequestBody,
    ): Response<MessageJson>

    @Multipart
    @PUT("$ApiVersion/account/{idAccount}")
    suspend fun updateAccount(
        @Part("account_name") name: RequestBody,
        @Path("idAccount") idAccount: Int,
    ): Response<MessageJson>

    @GET("$ApiVersion/account")
    suspend fun searchAccount(@Query("k") keyword: String): Response<ListAccountJson>

    @GET("$ApiVersion/account/{idAccount}")
    suspend fun getAccount(
        @Path("idAccount") idAccount: Int
    ): Response<SingleAccountJson>

    @GET("$ApiVersion/account/{idAccount}/songs")
    suspend fun getSongsOfAccount(
        @Path("idAccount") idAccount: Int,
    ): Response<ListSongJson>

    @GET("$ApiVersion/account/songs")
    suspend fun getMySongs(): Response<ListSongJson>

    @GET("$ApiVersion/account/{idAccount}/follower")
    suspend fun getFollowers(
        @Path("idAccount") idAccount: Int
    ): Response<ListAccountJson>

    @GET("$ApiVersion/account/{idAccount}/following")
    suspend fun getFollowings(
        @Path("idAccount") idAccount: Int
    ): Response<ListAccountJson>

    @GET("$ApiVersion/account/{idAccount}/album")
    suspend fun getAlbumsOfAccount(
        @Path("idAccount") idAccount: Int,
    ): Response<ListAlbumJson>

    @GET("$ApiVersion/account/{idAccount}/playlist")
    suspend fun getPlaylistsOfAccount(
        @Path("idAccount") idAccount: Int,
    ): Response<ListPlaylistJson>

    @GET("$ApiVersion/account/hot")
    suspend fun getTopAccounts(): Response<ListAccountJson>

    @POST("$ApiVersion/follow/{idAccount}")
    suspend fun followAccount(@Path("idAccount") idAccount: Int): Response<MessageJson>

    @DELETE("$ApiVersion/follow/{idAccount}")
    suspend fun unFollowAccount(@Path("idAccount") idAccount: Int): Response<MessageJson>

    @PUT("$ApiVersion/account/change/password")
    suspend fun changePassword(@Body modal: ChangePasswordModal): Response<MessageJson>

    @GET("$ApiVersion/account/block/list")
    suspend fun getLockAccounts(
        @HeaderMap header: Map<String, String> = NetworkHelper.getAuthorizationHeader()
    ): Response<ListAccountJson>

    @PUT("$ApiVersion/account/{idAccount}/block")
    suspend fun lockAccount(
        @Path("idAccount") idAccount: Int,
        @HeaderMap header: Map<String, String> = NetworkHelper.getAuthorizationHeader()
    ): Response<MessageJson>

    @PUT("$ApiVersion/account/{idAccount}/unblock")
    suspend fun unlockAccount(
        @Path("idAccount") idAccount: Int,
        @HeaderMap header: Map<String, String> = NetworkHelper.getAuthorizationHeader()
    ): Response<MessageJson>
}