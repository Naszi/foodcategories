package com.naszi.mobilapp.foodcategories.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naszi.mobilapp.foodcategories.database.Graph
import com.naszi.mobilapp.foodcategories.model.CategoriesState
import com.naszi.mobilapp.foodcategories.model.database.Comment
import com.naszi.mobilapp.foodcategories.repository.CommentRepository
import com.naszi.mobilapp.foodcategories.service.foodCategoriesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val commentRepository: CommentRepository = Graph.commentRepository
): ViewModel() {
    private val _categoriesState = mutableStateOf(CategoriesState())
    val categoriesState: State<CategoriesState> = _categoriesState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = foodCategoriesService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching categories ${e.message}"
                )
            }
        }
    }

    fun addComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            commentRepository.addComment(comment)
        }
    }

    fun deleteComment(comment: Comment) {
        viewModelScope.launch {
            commentRepository.deleteComment(comment)
        }
    }

    fun getCommentById(id: Long): Flow<Comment> {
        return commentRepository.getCommentById(id)
    }
}