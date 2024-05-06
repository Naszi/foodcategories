package com.naszi.mobilapp.foodcategories.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naszi.mobilapp.foodcategories.repository.CommentRepository

object Graph {
    lateinit var database: CommentDatabase
    val commentRepository by lazy {
        CommentRepository(commentDao = database.commentDao())
    }

    fun provider(context: Context) {
        database = Room.databaseBuilder(
            context = context,
            CommentDatabase::class.java,
            name = "comment.db"
        ).build()
    }
}