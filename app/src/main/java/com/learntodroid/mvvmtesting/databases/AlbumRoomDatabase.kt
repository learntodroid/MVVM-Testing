package com.learntodroid.mvvmtesting.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.learntodroid.mvvmtesting.daos.AlbumDao
import com.learntodroid.mvvmtesting.models.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = arrayOf(Album::class), version = 1, exportSchema = false)
abstract class AlbumRoomDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object {
        @Volatile
        private var INSTANCE: AlbumRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AlbumRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumRoomDatabase::class.java,
                    "album_database"
                ).addCallback(GoalDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class GoalDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.albumDao())
                }
            }
        }

        suspend fun populateDatabase(albumDao: AlbumDao) {
            // Delete all content here.
            albumDao.deleteAll()

            albumDao.insert(
                Album(
                    UUID.randomUUID().toString(),
                    "Encore",
                    "Eminem",
                    "https://via.placeholder.com/150"
//                    "https://ia902708.us.archive.org/14/items/mbid-c71fe58f-7a71-31a8-a41b-7e7351360992/mbid-c71fe58f-7a71-31a8-a41b-7e7351360992-9239948073.jpg"
                )
            )

//            goalDao.insert(
//                Goal(
//                    UUID.randomUUID().toString(),
//                    "Running",
//                    "Run around in a circle for 10 seconds straight",
//                    "Jocular",
//                    0,
//                    0,
//                    10
//                )
//            )
//
//            goalDao.insert(
//                Goal(
//                    UUID.randomUUID().toString(),
//                    "Reading",
//                    "Spend 30 minutes a day reading",
//                    "Education",
//                    0,
//                    30,
//                    0
//                )
//            )
//
//            goalDao.insert(
//                Goal(
//                    UUID.randomUUID().toString(),
//                    "Exercise",
//                    "Spend 45 minutes a day at the gym",
//                    "Health & Fitness",
//                    0,
//                    45,
//                    0
//                )
//            )
//
//            goalDao.insert(
//                Goal(
//                    UUID.randomUUID().toString(),
//                    "Blogging",
//                    "Spend 1 hour a day writing on my blog",
//                    "Projects",
//                    1,
//                    0,
//                    0
//                )
//            )
        }
    }
}