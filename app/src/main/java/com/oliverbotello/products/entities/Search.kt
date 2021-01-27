package com.oliverbotello.products.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search")
data class Search (
    @PrimaryKey
    @ColumnInfo(name = "value")
    var value: String = ""
)