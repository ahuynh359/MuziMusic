package com.team15.muzimusic.data.repositories

import com.team15.muzimusic.data.models.LabelJson
import com.team15.muzimusic.data.models.SearchJson
import com.team15.muzimusic.data.services.account.AccountRemoteService
import com.team15.muzimusic.data.services.label.LabelRemoteService
import com.team15.muzimusic.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class LabelRepository @Inject constructor(
    private val labelRemoteService: LabelRemoteService,
) {

    suspend fun searchLabel(keyword: String, maxLabel : Int): LabelJson? {
        return labelRemoteService.searchLabel(keyword,maxLabel)
    }
}