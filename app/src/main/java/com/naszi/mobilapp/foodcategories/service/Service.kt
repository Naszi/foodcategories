package com.naszi.mobilapp.foodcategories.service

import com.naszi.mobilapp.foodcategories.service.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private var retrofit = Retrofit.Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val foodCategoriesService = retrofit.create(ApiService::class.java)
