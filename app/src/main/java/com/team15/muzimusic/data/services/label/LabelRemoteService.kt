package com.team15.muzimusic.data.services.label

import com.team15.muzimusic.base.network.BaseRemoteService
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.data.apis.LabelAPI
import com.team15.muzimusic.data.apis.SongAPI
import com.team15.muzimusic.data.models.LabelJson
import javax.inject.Inject

class LabelRemoteService @Inject constructor(
    private val labelAPI: LabelAPI
) : BaseRemoteService() {

    suspend fun searchLabel(keyword: String, maxLabel: Int): LabelJson? {
        val result = callApi { labelAPI.searchLabel(keyword,maxLabel) }
        return if (result is NetworkResult.Success) {
            result.body
        } else {
            null
        }
    }
}