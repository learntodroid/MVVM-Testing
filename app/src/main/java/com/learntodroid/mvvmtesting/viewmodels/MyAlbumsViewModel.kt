package com.learntodroid.mvvmtesting.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.learntodroid.mvvmtesting.databases.AlbumRoomDatabase
import com.learntodroid.mvvmtesting.models.Album
import com.learntodroid.mvvmtesting.repositories.AlbumsRepository
import kotlinx.coroutines.launch

class MyAlbumsViewModel(application: Application): AndroidViewModel(application) {
    private val repository: AlbumsRepository
    var albums: LiveData<List<Album>>

    init {
        val albumDao = AlbumRoomDatabase.getDatabase(application, viewModelScope).albumDao()
        repository = AlbumsRepository(albumDao)
        albums = repository.albums
    }

    fun insert(album: Album) = viewModelScope.launch {
        repository.insert(album)
    }
}