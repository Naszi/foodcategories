package com.naszi.mobilapp.foodcategories.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment-table")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "category-item-id")
    val categoryItemId: Int = 0,
    @ColumnInfo(name = "comment")
    val comment: String = ""
)
