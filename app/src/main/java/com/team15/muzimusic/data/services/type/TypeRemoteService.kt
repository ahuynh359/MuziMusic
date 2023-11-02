package com.team15.muzimusic.data.services.type

import com.team15.muzimusic.base.network.BaseRemoteService
import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.data.apis.TypeAPI
import com.team15.muzimusic.data.apis.TypeModal
import com.team15.muzimusic.data.models.ListAlbumJson
import com.team15.muzimusic.data.models.ListTypeJson
import com.team15.muzimusic.data.models.MessageJson
import com.team15.muzimusic.data.models.Type
import com.team15.muzimusic.data.models.TypeJson
import retrofit2.http.GET
import javax.inject.Inject

class TypeRemoteService @Inject constructor(
    private val typeAPI: TypeAPI
) : BaseRemoteService() {




    suspend fun addType(type: Type): NetworkResult<MessageJson> {
        return callApi { typeAPI.addType(TypeModal(type.name, type.description)) }
    }

    suspend fun updateType(type: Type): NetworkResult<MessageJson> {
        return callApi { typeAPI.updateType(type.idType, TypeModal(type.name, type.description)) }
    }

    suspend fun deleteType(type: Type): NetworkResult<MessageJson> {
        return callApi { typeAPI.deleteType(type.idType) }
    }
}