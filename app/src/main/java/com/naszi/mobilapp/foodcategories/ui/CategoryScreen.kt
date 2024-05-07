package com.naszi.mobilapp.foodcategories.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.naszi.mobilapp.foodcategories.R
import com.naszi.mobilapp.foodcategories.model.CategoriesState
import com.naszi.mobilapp.foodcategories.model.Category
import com.naszi.mobilapp.foodcategories.utils.Constants.FOOD_CATEGORY

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    viewState: CategoriesState,
    navigateToDetail: (Category) -> Unit
) {
    Scaffold(
        topBar = {
            AppBarView(title = FOOD_CATEGORY)
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            when {
                viewState.loading -> {
                    CircularProgressIndicator(modifier.align(Alignment.Center))
                }

                viewState.error != null -> {
                    Text(text = "ERROR OCCURRED")
                }

                else -> {
                    CategoriesScreen(categories = viewState.list, navigateToDetail)
                }
            }
        }
    }
}

@Composable
fun CategoriesScreen(
    categories: List<Category>,
    navigateToDetail: (Category) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(categories) {
            category ->
            CategoryItem(category = category, navigateToDetail)
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    navigateToDetail: (Category) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { navigateToDetail(category) },
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.teal_200)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(category.strCategoryThumb),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1F)
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = category.strCategory,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
