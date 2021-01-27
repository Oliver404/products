package com.oliverbotello.products.database

import com.oliverbotello.products.entities.Search

abstract class DBManager {
    abstract fun getSearchs(): List<Search>

    abstract fun getSearchs(search: String): List<Search>

    abstract fun insertSearch(search: Search): Unit

    abstract fun insertSearchs(lstSearchs: List<Search>): Unit

    abstract fun delete(employee: Search)

    abstract fun deleteAll()
}