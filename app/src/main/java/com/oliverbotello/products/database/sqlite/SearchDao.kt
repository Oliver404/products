package com.oliverbotello.products.database.sqlite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.oliverbotello.products.entities.Search

@Dao
interface SearchDao {
    @Query("SELECT * FROM search")
    fun getAll(): List<Search>

    @Query("SELECT * FROM search WHERE value LIKE :value")
    fun getAll(value: String): List<Search>

    @Insert
    fun insert(vararg search: Search)

    @Insert
    fun insertAll(search: List<Search>)

    @Delete
    fun delete(search: Search)

    @Query("DELETE FROM search")
    fun deleteAll()
}