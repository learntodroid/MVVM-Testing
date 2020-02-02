package com.learntodroid.mvvmtesting.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.learntodroid.mvvmtesting.R
import com.learntodroid.mvvmtesting.models.Album
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumsRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var albums: List<Album> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is AlbumViewHolder -> {
                holder.bind(albums.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    fun setAlbums(albumsList: List<Album>) {
        albums = albumsList
    }

}

class AlbumViewHolder(v: View): RecyclerView.ViewHolder(v) {
    private var title : TextView = v.item_album_title
    private var artist : TextView = v.item_album_artist
    private var cover : ImageView = v.item_album_cover

    fun bind(album: Album) {
        title.setText(album.title)
        artist.setText(album.artist)

        cover.load(album.coverArtLink) {
            placeholder(R.drawable.ic_album_black_24dp)
        }
        cover.contentDescription = "$album.title by ${album.artist}"
    }
}