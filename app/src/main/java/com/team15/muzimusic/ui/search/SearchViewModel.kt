package com.team15.muzimusic.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.common.Constants

import com.team15.muzimusic.data.database.entities.SearchHistoryEntity
import com.team15.muzimusic.data.models.*
import com.team15.muzimusic.data.repositories.LabelRepository
import com.team15.muzimusic.data.repositories.SearchHistoryRepository
import com.team15.muzimusic.data.repositories.SongRepository
import com.team15.muzimusic.data.repositories.TypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val songRepository: SongRepository,
    private val searchHistoryRepository: SearchHistoryRepository,
    private val labelRepository: LabelRepository,
) : BaseViewModel() {

    val isSearchDone = MutableLiveData(false)

    val searchHistoryList = MutableLiveData<List<SearchHistoryEntity>>()

    val songs = MutableLiveData<List<Song>>()
    val playlists = MutableLiveData<List<Playlist>>()
    val accounts = MutableLiveData<List<Account>>()

    val songType = MutableLiveData<List<Song>>()

    val labels = MutableLiveData<Label>()
    val _allType = MutableLiveData<List<Type>>()
    var labelType = MutableLiveData<Type>()
    val allType: List<Type>? = _allType.value


    private fun getSingleTypeOfSingleLable() {
        getAllType()
        if (allType != null) {
            val labelToMatch = labels.value?.result?.get(0)?.label ?: ""
            labelType.value = allType.find { it.name == labelToMatch }

        }

    }


    private fun getAllType() {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            val result = songRepository.getAllTypes()
            result.let {
                _allType.postValue(it)
            }
        }
        isSearchDone.postValue(true)
        registerEventParentJobFinish()
    }

    fun searchLabel(sentence: String, max: Int) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            val result = labelRepository.searchLabel(sentence, max)
            result?.let {
                labels.postValue(it.toLabel())

            }
        }
        isSearchDone.postValue(true)
        registerEventParentJobFinish()
    }

    fun getSearchHistory() {
        viewModelScope.launch {
            searchHistoryList.postValue(searchHistoryRepository.getAllSearchHistory())
        }
    }

    fun saveSearchKeyword(keyword: String) {
        viewModelScope.launch {
            searchHistoryRepository.insert(SearchHistoryEntity(keyword, Date()))
        }
    }

    fun search(keyword: String) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            val result = songRepository.search(keyword)
            result?.let {
                songs.postValue(it.songs.toListSong())
                playlists.postValue(it.playlists.toListPlaylist())
                accounts.postValue(it.accounts.toListAccount())
            }
        }
        isSearchDone.postValue(true)
        registerEventParentJobFinish()
    }

    fun getSongsOfType() {

        isLoading.postValue(true)
        getSingleTypeOfSingleLable()
        parentJob = viewModelScope.launch {
            songType.postValue(labelType.value?.idType?.let { songRepository.getSongsOfType(it, 1) })

        }
        registerEventParentJobFinish()

    }

}