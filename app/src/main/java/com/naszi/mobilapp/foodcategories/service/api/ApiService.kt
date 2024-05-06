package com.naszi.mobilapp.foodcategories.service.api

import com.naszi.mobilapp.foodcategories.model.CategoriesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}