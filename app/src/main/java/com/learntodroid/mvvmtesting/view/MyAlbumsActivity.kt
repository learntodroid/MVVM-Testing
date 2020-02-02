package com.learntodroid.mvvmtesting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learntodroid.mvvmtesting.R
import com.learntodroid.mvvmtesting.adapters.AlbumsRecyclerAdapter
import com.learntodroid.mvvmtesting.viewmodels.MyAlbumsViewModel

class MyAlbumsActivity : AppCompatActivity() {
    private lateinit var albumsRecyclerView: RecyclerView
    private lateinit var albumsAdapter: AlbumsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        albumsRecyclerView = findViewById(R.id.activity_albums_albumsrecyclerView)
        albumsRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        albumsAdapter = AlbumsRecyclerAdapter()
        albumsRecyclerView.adapter = albumsAdapter

        val myAlbumsViewModel: MyAlbumsViewModel by viewModels()
        myAlbumsViewModel.albums.observe(this, Observer { albums ->
            albums?.let {
                Log.i("observe", albums.size.toString())
                albumsAdapter.setAlbums(albums)
            }
        })
    }
}
