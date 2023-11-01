package com.team15.muzimusic.data.database.daos

import androidx.room.*
import com.team15.muzimusic.data.database.entities.SongTypeEntity
import com.team15.muzimusic.data.database.entities.TypeEntity

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(typeEntity: TypeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongType(songTypeEntity: SongTypeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListType(types: List<TypeEntity>)

    @Update
    suspend fun updateType(typeEntity: TypeEntity)

    @Delete
    suspend fun deleteType(typeEntity: TypeEntity)

    @Query("SELECT * FROM TypeEntity")
    suspend fun getAllType(): List<TypeEntity>

    @Query("DELETE FROM TypeEntity")
    suspend fun deleteAllType()
}