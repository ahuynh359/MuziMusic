package com.team15.muzimusic.data.services.album

import com.team15.muzimusic.data.database.daos.AlbumDao
import com.team15.muzimusic.data.database.entities.AlbumEntity
import javax.inject.Inject

class AlbumLocalService @Inject constructor(
    private val albumDao: AlbumDao
) {

    suspend fun getAllAlbum(): List<AlbumEntity> {
        return albumDao.getAllAlbum()
    }

    suspend fun saveAlbum(albumEntity: AlbumEntity) {
        return albumDao.insertAlbum(albumEntity)
    }

    suspend fun deleteAllAlbum() {
        return albumDao.deleteAllAlbum()
    }
}