package com.naszi.mobilapp.foodcategories.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naszi.mobilapp.foodcategories.database.Graph
import com.naszi.mobilapp.foodcategories.model.CategoriesState
import com.naszi.mobilapp.foodcategories.model.CategoryWithComment
import com.naszi.mobilapp.foodcategories.model.database.Comment
import com.naszi.mobilapp.foodcategories.repository.CommentRepository
import com.naszi.mobilapp.foodcategories.service.foodCategoriesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(
    private val commentRepository: CommentRepository = Graph.commentRepository
) : ViewModel() {
    private val _categoriesState = mutableStateOf(CategoriesState())
    val categoriesState: State<CategoriesState> = _categoriesState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val commentsDeferred = async { commentRepository.getAllComments() }
                val categoriesDeferred = async { foodCategoriesService.getCategories() }
                val comments = commentsDeferred.await()
                val categories = categoriesDeferred.await()
                val categoriesList = categories.categories
                val commentsList = comments.toList().ifEmpty { emptyList() }
                val sortedCommentsList = if (commentsList.isNotEmpty()) {
                    commentsList.sortedBy { it.categoryItemId }
                } else {
                    emptyList<Comment>()
                }
                val categoriesWithComments = mutableListOf<CategoryWithComment>()
                for (category in categoriesList) {
                    val commentForCategory = sortedCommentsList.find {
                        it.categoryItemId == category.idCategory.toInt()
                    }
                    val categoryWithComment = CategoryWithComment(
                        idCategory = category.idCategory,
                        strCategory = category.strCategory,
                        strCategoryThumb = category.strCategoryThumb,
                        strCategoryDescription = category.strCategoryDescription,
                        id = commentForCategory?.id ?: 0L,
                        comment = commentForCategory?.comment ?: "",
                        hasComment = commentForCategory != null
                    )
                    categoriesWithComments.add(categoryWithComment)
                }

                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    list = categoriesWithComments,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun addComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            commentRepository.addComment(comment)
            val category =
                _categoriesState.value.list.find { it.idCategory == comment.categoryItemId.toString() }
            category?.hasComment = true
            _categoriesState.value = _categoriesState.value.copy(list = _categoriesState.value.list)
            fetchCategories()
        }
    }

    fun updateComment(categoryId: Int, comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            commentRepository.updateComment(comment)
            _categoriesState.value = _categoriesState.value.copy(
                list = _categoriesState.value.list.map { category ->
                    if (category.idCategory.toInt() == categoryId) {
                        category.copy(comment = comment.comment)
                    } else {
                        category
                    }
                }
            )
            fetchCategories()
        }
    }

    fun deleteComment(comment: Comment) {
        viewModelScope.launch {
            commentRepository.deleteComment(comment)
            val updatedCategories = _categoriesState.value.list.map { category ->
                if (category.idCategory == comment.categoryItemId.toString()) {
                    category.copy(
                        hasComment = false,
                        id = 0L,
                        comment = ""
                    )
                } else {
                    category
                }
            }
            _categoriesState.value = _categoriesState.value.copy(list = updatedCategories)
        }
    }
}