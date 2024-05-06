package com.naszi.mobilapp.foodcategories.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naszi.mobilapp.foodcategories.model.database.Comment

@Database(
    entities = [Comment::class],
    version = 1,
    exportSchema = false
)
abstract class CommentDatabase: RoomDatabase() {
    abstract fun commentDao(): CommentDao
}