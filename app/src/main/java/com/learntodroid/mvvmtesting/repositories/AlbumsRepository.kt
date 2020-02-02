package com.learntodroid.mvvmtesting.repositories

import androidx.lifecycle.LiveData
import com.learntodroid.mvvmtesting.daos.AlbumDao
import com.learntodroid.mvvmtesting.models.Album

class AlbumsRepository(
    private var albumDao: AlbumDao
) {
    var albums: LiveData<List<Album>> = albumDao.getAlbums()

    suspend fun insert(album: Album) {
        albumDao.insert(album)
    }
}