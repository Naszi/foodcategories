package com.naszi.mobilapp.foodcategories.utils

object Constants {
    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    const val END_POINT_CATEGORIES = "categories.php"

    const val FOOD_CATEGORY = "Food Categories"
    const val CATEGORY_DETAILS = "Category Details"

    const val GET_COMMENT_BY_ID = "SELECT * FROM `comment-table` WHERE id=:id"
    const val GET_ALL_COMMENTS = "SELECT * FROM `comment-table`"
}