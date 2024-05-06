package com.naszi.mobilapp.foodcategories.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.naszi.mobilapp.foodcategories.model.database.Comment
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CommentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addComment(comment: Comment)
    @Delete
    abstract suspend fun deleteComment(comment: Comment)
    @Query("SELECT * FROM `comment-table` WHERE id=:id")
    abstract fun getCommentById(id: Long): Flow<Comment>
}