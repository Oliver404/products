package com.oliverbotello.products.database.sqlite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oliverbotello.products.entities.Search

@Database(entities = arrayOf(Search::class), version = 1)
abstract class SearchDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}