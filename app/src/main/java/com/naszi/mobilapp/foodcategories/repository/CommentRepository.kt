package com.naszi.mobilapp.foodcategories.repository

import com.naszi.mobilapp.foodcategories.database.CommentDao
import com.naszi.mobilapp.foodcategories.model.database.Comment
import kotlinx.coroutines.flow.Flow

class CommentRepository(
    private val commentDao: CommentDao
) {
    suspend fun addComment(comment: Comment) {
        commentDao.addComment(comment)
    }
    suspend fun deleteComment(comment: Comment) {
        commentDao.deleteComment(comment)
    }
    fun getCommentById(id: Long): Flow<Comment> {
        return commentDao.getCommentById(id)
    }

    suspend fun getAllComments(): List<Comment> {
        return commentDao.getAllComments()
    }
}