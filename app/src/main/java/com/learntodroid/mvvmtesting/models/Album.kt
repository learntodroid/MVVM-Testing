package com.learntodroid.mvvmtesting.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class Album(
    @PrimaryKey var id: String,
    var title: String,
    var artist: String,
    var coverArtLink: String
) {

}