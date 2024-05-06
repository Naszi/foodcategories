package com.naszi.mobilapp.foodcategories.model

data class CategoriesState(
    val loading: Boolean = true,
    val list: List<Category> = emptyList(),
    val error: String? = null
)
