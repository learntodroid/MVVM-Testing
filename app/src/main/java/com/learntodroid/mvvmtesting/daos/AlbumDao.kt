package com.learntodroid.mvvmtesting.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.learntodroid.mvvmtesting.models.Album

@Dao
interface AlbumDao {
    @Query("SELECT * from album_table ORDER BY title ASC")
    fun getAlbums(): LiveData<List<Album>>

    @Insert()
    suspend fun insert(album: Album)

    @Query("DELETE FROM album_table")
    suspend fun deleteAll()
}