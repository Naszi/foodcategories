package com.naszi.mobilapp.foodcategories.model

data class CategoriesState(
    val loading: Boolean = true,
    val list: List<CategoryWithComment> = emptyList(),
    val error: String? = null
)
