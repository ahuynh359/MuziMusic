package com.team15.muzimusic.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.data.database.entities.SearchHistoryEntity
import com.team15.muzimusic.data.models.Account
import com.team15.muzimusic.data.models.Label
import com.team15.muzimusic.data.models.Playlist
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.models.Type
import com.team15.muzimusic.data.models.toListAccount
import com.team15.muzimusic.data.models.toListPlaylist
import com.team15.muzimusic.data.models.toListSong
import com.team15.muzimusic.data.repositories.LabelRepository
import com.team15.muzimusic.data.repositories.SearchHistoryRepository
import com.team15.muzimusic.data.repositories.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Date
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

  /*  fun search(keyword: String) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            val result = songRepository.search(keyword)
            result?.let {
                songs.postValue(it.songs.toListSong())
                playlists.postValue(it.playlists.toListPlaylist())
                accounts.postValue(it.accounts.toListAccount())

            }

            val result2 = labelRepository.searchLabel(keyword, 1)
            result2?.let {
                labels.postValue(it.toLabel())

            }
            val labelToMatch = labels.value?.result?.get(0)?.label ?: ""
            val num = when (labelToMatch) {
                "sadness" -> 1
                "love" -> 2
                "suprise" -> 3
                "joy" -> 4
                else -> 6
            }
            val list = songRepository.getSongsOfType(num, 1)

            songType.postValue(list)
        }
        isSearchDone.postValue(true)
        registerEventParentJobFinish()
    }*/

    fun search(keyword: String) {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch(Dispatchers.IO) {
            val result = async { songRepository.search(keyword) }
            val result2 = async { labelRepository.searchLabel(keyword, 1) }

            result.await()?.let {
                songs.postValue(it.songs.toListSong())
                playlists.postValue(it.playlists.toListPlaylist())
                accounts.postValue(it.accounts.toListAccount())
                isSearchDone.postValue(true)
                registerEventParentJobFinish()
            }
            result2.await()?.let {
                labels.postValue(it.toLabel())
                val num = when (it.result[0].label) {
                    "sadness" -> 1
                    "love" -> 2
                    "suprise" -> 3
                    "joy" -> 4
                    else -> 6
                }
                val list = songRepository.getSongsOfType(num, 1)

                songType.postValue(list)
            }
        }
//        parentJob = viewModelScope.launch(Dispatchers.IO) {
//            val result = songRepository.search(keyword)
//            result?.let {
//                songs.postValue(it.songs.toListSong())
//                playlists.postValue(it.playlists.toListPlaylist())
//                accounts.postValue(it.accounts.toListAccount())
//
//            }
//
//            val result2 = labelRepository.searchLabel(keyword, 1)
//            result2?.let {
//                labels.postValue(it.toLabel())
//
//            }
//            val labelToMatch = result2?.result?.get(0)?.label ?: ""
//            val num = when (labelToMatch) {
//                "sadness" -> 1
//                "love" -> 2
//                "suprise" -> 3
//                "joy" -> 4
//                else -> 6
//            }
//            val list = songRepository.getSongsOfType(num, 1)
//
//            songType.postValue(list)
//        }
//        isSearchDone.postValue(true)
//        registerEventParentJobFinish()
    }


}