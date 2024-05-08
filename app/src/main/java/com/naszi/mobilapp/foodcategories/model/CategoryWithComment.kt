package com.naszi.mobilapp.foodcategories.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryWithComment(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String,
    val id: Long,
    val comment: String = "",
    var hasComment: Boolean = false
): Parcelable
