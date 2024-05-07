package com.naszi.mobilapp.foodcategories.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.naszi.mobilapp.foodcategories.R
import com.naszi.mobilapp.foodcategories.model.Category
import com.naszi.mobilapp.foodcategories.utils.Constants.CATEGORY_DETAILS
import com.naszi.mobilapp.foodcategories.viewmodel.MainViewModel

@Composable
fun CategoryDetailScreen(
    viewModel: MainViewModel,
    category: Category,
    navController: NavController
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    var isAddPopupVisible by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            AppBarView(title = CATEGORY_DETAILS) {navController.navigateUp()}
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                backgroundColor = colorResource(id = R.color.teal_700),
                onClick = {
                    isAddPopupVisible = true
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        scaffoldState = scaffoldState
    ) {
        Surface(
            color = colorResource(id = R.color.teal_200)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = category.strCategory,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Image(
                    painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
                    contentDescription = "${category.strCategory} Thumbail",
                    modifier = Modifier
                        .size(200.dp)
                        .aspectRatio(1F)
                )
                Text(
                    text = category.strCategoryDescription,
                    textAlign = TextAlign.Justify,
                    style = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
                if (isAddPopupVisible) {
                    AddCommentPopup(
                        viewModel = viewModel,
                        category = category,
                        onDismiss = { isAddPopupVisible = false }
                    )
                }
            }
        }
    }
}