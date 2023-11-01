package com.team15.muzimusic.data.apis

import com.team15.muzimusic.common.Config
import com.team15.muzimusic.data.models.LabelJson
import com.team15.muzimusic.data.models.SearchResponse
import com.team15.muzimusic.data.models.SingleAccountJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LabelAPI {

    @GET("${Config.ApiAIVersion}/")
    suspend fun searchLabel(
        @Query("sentence") param1: String,
        @Query("max_label") param2: Int,
    ): Response<LabelJson>


}