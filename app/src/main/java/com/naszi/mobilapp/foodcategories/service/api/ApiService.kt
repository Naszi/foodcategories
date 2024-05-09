package com.naszi.mobilapp.foodcategories.service.api

import com.naszi.mobilapp.foodcategories.model.CategoriesResponse
import com.naszi.mobilapp.foodcategories.utils.Constants.END_POINT_CATEGORIES
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT_CATEGORIES)
    suspend fun getCategories(): CategoriesResponse
}