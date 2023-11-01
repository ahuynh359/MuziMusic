package com.team15.muzimusic.data.repositories

import com.team15.muzimusic.data.database.entities.SearchHistoryEntity
import com.team15.muzimusic.data.services.search_history.SearchHistoryLocalService
import com.team15.muzimusic.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(
    private val localService: SearchHistoryLocalService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getAllSearchHistory(): List<SearchHistoryEntity> {
        return withContext(dispatcher) {
            localService.getAllSearchHistory()
        }
    }

    suspend fun insert(searchHistoryEntity: SearchHistoryEntity) {
        withContext(dispatcher) {
            localService.insert(searchHistoryEntity)
        }
    }

    suspend fun deleteAll() {
        withContext(dispatcher) {
            localService.deleteAll()
        }
    }
}