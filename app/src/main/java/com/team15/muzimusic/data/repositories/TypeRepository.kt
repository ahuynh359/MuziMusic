package com.team15.muzimusic.data.repositories

import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.common.Config
import com.team15.muzimusic.data.database.entities.TypeEntity
import com.team15.muzimusic.data.models.Album
import com.team15.muzimusic.data.models.ListTypeJson
import com.team15.muzimusic.data.models.MessageJson
import com.team15.muzimusic.data.models.Type
import com.team15.muzimusic.data.models.TypeJson
import com.team15.muzimusic.data.models.toListAlbum
import com.team15.muzimusic.data.models.toListType
import com.team15.muzimusic.data.services.type.TypeRemoteService
import com.team15.muzimusic.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

class TypeRepository @Inject constructor(
    private val remoteService: TypeRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {



    suspend fun addType(type: Type): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.addType(type)
        }
    }

    suspend fun updateType(type: Type): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.updateType(type)
        }
    }

    suspend fun deleteType(type: Type): NetworkResult<MessageJson> {
        return withContext(dispatcher) {
            remoteService.deleteType(type)
        }
    }
}