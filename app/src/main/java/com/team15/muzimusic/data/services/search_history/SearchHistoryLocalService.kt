package com.team15.muzimusic.data.services.search_history

import com.team15.muzimusic.data.database.daos.SearchHistoryDAO
import com.team15.muzimusic.data.database.entities.SearchHistoryEntity
import javax.inject.Inject

class SearchHistoryLocalService @Inject constructor(
    private val searchHistoryDAO: SearchHistoryDAO
) {

    suspend fun getAllSearchHistory(): List<SearchHistoryEntity> {
        return searchHistoryDAO.getAllSearchHistory()
    }

    suspend fun insert(searchHistoryEntity: SearchHistoryEntity) {
        searchHistoryDAO.insert(searchHistoryEntity)
    }

    suspend fun deleteAll() {
        searchHistoryDAO.deleteAll()
    }
}