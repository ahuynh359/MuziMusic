package com.team15.muzimusic.data.services.song

import com.team15.muzimusic.data.database.daos.AccountDAO
import com.team15.muzimusic.data.database.daos.AlbumDao
import com.team15.muzimusic.data.database.daos.SongDAO
import com.team15.muzimusic.data.database.daos.TypeDao
import com.team15.muzimusic.data.database.entities.SongFullEntity
import com.team15.muzimusic.data.database.entities.SongSingersEntity
import javax.inject.Inject

class SongLocalService @Inject constructor(
    private val songDAO: SongDAO,
    private val accountDAO: AccountDAO,
    private val typeDao: TypeDao,
    private val albumDao: AlbumDao,
) {

    // ----- Top Song -----
    suspend fun getListTopSongs(): List<SongFullEntity> {
        return songDAO.getListTopSongs()
    }

    suspend fun saveListTopSongs(songs: List<SongFullEntity>) {
        songs.forEach { saveSong(it) }
    }

    suspend fun saveSong(song: SongFullEntity) {
        typeDao.insertListType(song.types)
        accountDAO.insert(song.account)
        accountDAO.insertAll(song.singers)
        albumDao.insertAlbum(song.album)
        songDAO.insertSong(song.song)

        song.singers.forEach {
            songDAO.insertSongSingers(SongSingersEntity(song.song.idSong, it.idAccount))
        }
    }

    suspend fun deleteListTopSongs() {
        songDAO.deleteListTopSongs()
    }


    suspend fun saveSongSingers(songSingersEntity: SongSingersEntity) {
        songDAO.insertSongSingers(songSingersEntity)
    }

    suspend fun updateSongImagePath(idSong: Int, path: String) {
        songDAO.updateSongImagePath(idSong, path)
    }

    suspend fun updateSongFilePath(idSong: Int, path: String) {
        songDAO.updateSongFilePath(idSong, path)
    }
}