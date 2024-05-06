package com.naszi.mobilapp.foodcategories.navigation

sealed class Screen(val route: String) {
    data object CategoryScreen: Screen(route = "category_screen")
    data object DetailScreen: Screen(route = "detail_screen")
}