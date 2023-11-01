package com.team15.muzimusic.data.repositories

import com.team15.muzimusic.base.network.NetworkResult
import com.team15.muzimusic.data.models.MessageJson
import com.team15.muzimusic.data.models.Type
import com.team15.muzimusic.data.services.type.TypeRemoteService
import com.team15.muzimusic.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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