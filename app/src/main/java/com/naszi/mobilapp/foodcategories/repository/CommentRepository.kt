package com.naszi.mobilapp.foodcategories.repository

import com.naszi.mobilapp.foodcategories.database.CommentDao
import com.naszi.mobilapp.foodcategories.model.database.Comment

class CommentRepository(
    private val commentDao: CommentDao
) {
    suspend fun addComment(comment: Comment) {
        commentDao.addComment(comment)
    }

    suspend fun deleteComment(comment: Comment) {
        commentDao.deleteComment(comment)
    }

    suspend fun getAllComments(): List<Comment> {
        return commentDao.getAllComments()
    }

    suspend fun updateComment(comment: Comment) {
        commentDao.updateComment(comment)
    }
}