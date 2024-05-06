package com.naszi.mobilapp.foodcategories

import android.app.Application
import com.naszi.mobilapp.foodcategories.database.Graph

class FoodCategoriesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provider(context = this)
    }
}