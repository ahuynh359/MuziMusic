package com.team15.muzimusic.data.apis

import com.team15.muzimusic.base.network.NetworkHelper
import com.team15.muzimusic.common.Config
import com.team15.muzimusic.data.models.ListTypeJson
import com.team15.muzimusic.data.models.MessageJson
import retrofit2.Response
import retrofit2.http.*

interface TypeAPI {

    @GET("${Config.ApiVersion}/type/all")
    suspend fun getAllTypes(): Response<ListTypeJson>

    @POST("${Config.ApiVersion}/type")
    suspend fun addType(
        @Body modal: TypeModal,
        @HeaderMap header: Map<String, String> = NetworkHelper.getAuthorizationHeader()
    ): Response<MessageJson>

    @PUT("${Config.ApiVersion}/type/{idType}")
    suspend fun updateType(
        @Path("idType") idType: Int,
        @Body modal: TypeModal,
        @HeaderMap header: Map<String, String> = NetworkHelper.getAuthorizationHeader()
    ): Response<MessageJson>

    @DELETE("${Config.ApiVersion}/type/{idType}")
    suspend fun deleteType(
        @Path("idType") idType: Int,
    ): Response<MessageJson>
}

data class TypeModal(val name: String, val description: String = "")