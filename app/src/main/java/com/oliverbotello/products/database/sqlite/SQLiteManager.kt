package com.oliverbotello.products.database.sqlite

import android.content.Context
import androidx.room.Room
import com.oliverbotello.products.entities.Search
import com.oliverbotello.products.database.DBManager

class SQLiteManager(context: Context) : DBManager() {
    companion object {
        private var dbManager: SearchDatabase?  = null
    }

    init {
        dbManager = Room.databaseBuilder(
            context,
            SearchDatabase::class.java,
            "db-searchs"
        ).build()
    }

    override fun getSearchs(): List<Search> =
        dbManager?.searchDao()?.getAll()?: listOf()

    override fun getSearchs(search: String): List<Search> =
        dbManager?.searchDao()?.getAll("%$search%")?: listOf()

    override fun insertSearch(search: Search) {
        dbManager?.searchDao()?.insert(search)
    }

    override fun insertSearchs(lstSearchs: List<Search>) {
        dbManager?.searchDao()?.insertAll(lstSearchs)
    }

    override fun delete(search: Search) {
        dbManager?.searchDao()?.delete(search)
    }

    override fun deleteAll() {
        dbManager?.searchDao()?.deleteAll()
    }
}