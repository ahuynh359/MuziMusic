package com.team15.muzimusic.ui.home.library.favoritesongs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.team15.muzimusic.base.viewmodels.BaseViewModel
import com.team15.muzimusic.common.Constants
import com.team15.muzimusic.data.models.Song
import com.team15.muzimusic.data.repositories.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteSongsViewModel @Inject constructor(
    private val songRepository: SongRepository
) : BaseViewModel() {

    var favoriteSongs = MutableLiveData<MutableList<Song>>()
    var hasMore = true

    fun fetchData() {
        isLoading.postValue(true)
        parentJob = viewModelScope.launch {
            if (hasMore) {
                var nextPage = 1;
                favoriteSongs.value?.let {
                    nextPage = it.size.div(Constants.SIZE_PER_PAGE) + 1
                }

                val list = songRepository.getLoveSongs(nextPage)
                if (list.size < Constants.SIZE_PER_PAGE) hasMore = false

                val currentList = favoriteSongs.value ?: mutableListOf()
                currentList.addAll(list)

                favoriteSongs.postValue(currentList)
            }
        }
        registerEventParentJobFinish()
    }

}