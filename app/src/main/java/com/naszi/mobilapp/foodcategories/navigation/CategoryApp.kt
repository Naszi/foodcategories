package com.naszi.mobilapp.foodcategories.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.naszi.mobilapp.foodcategories.model.Category
import com.naszi.mobilapp.foodcategories.ui.CategoryDetailScreen
import com.naszi.mobilapp.foodcategories.ui.CategoryScreen
import com.naszi.mobilapp.foodcategories.viewmodel.MainViewModel

@Composable
fun CategoryApp(
    navController: NavHostController
) {
    val categoriesViewModel: MainViewModel = viewModel()
    val viewState by categoriesViewModel.categoriesState

    NavHost(
        navController = navController,
        startDestination = Screen.CategoryScreen.route
    ) {
        composable(route = Screen.CategoryScreen.route) {
            CategoryScreen(
                viewState = viewState,
                navigateToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                    navController.navigate(Screen.DetailScreen.route)
                }
            )
        }
        composable(route = Screen.DetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
                    ?: Category("", "", "", "")
            CategoryDetailScreen(category = category)
        }
    }
}