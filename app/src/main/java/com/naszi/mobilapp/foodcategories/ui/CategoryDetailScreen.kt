package com.naszi.mobilapp.foodcategories.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.naszi.mobilapp.foodcategories.R
import com.naszi.mobilapp.foodcategories.model.CategoryWithComment
import com.naszi.mobilapp.foodcategories.model.database.Comment
import com.naszi.mobilapp.foodcategories.navigation.Screen
import com.naszi.mobilapp.foodcategories.utils.Constants.CATEGORY_DETAILS
import com.naszi.mobilapp.foodcategories.viewmodel.MainViewModel

@Composable
fun CategoryDetailScreen(
    viewModel: MainViewModel,
    category: CategoryWithComment,
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    var isAddOrEditPopupVisible by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            AppBarView(title = CATEGORY_DETAILS) { navController.navigateUp() }
        },
        floatingActionButton = {
            if (!category.hasComment) {
                FloatingActionButton(
                    modifier = Modifier.padding(all = 16.dp),
                    contentColor = Color.White,
                    backgroundColor = colorResource(id = R.color.teal_700),
                    onClick = {
                        isAddOrEditPopupVisible = true
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
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
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = category.strCategory,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Image(
                    painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
                    contentDescription = "${category.strCategory} Thumbail",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(150.dp)
                        .aspectRatio(1F)
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (category.hasComment) {
                    CommentView(
                        comment = category.comment,
                        onDelete = {
                            val comment = Comment(
                                id = category.id,
                                categoryItemId = category.idCategory.toInt(),
                                comment = category.comment
                            )
                            viewModel.deleteComment(comment = comment)
                            navController.navigate(Screen.CategoryScreen.route)
                        },
                        onEdit = { isAddOrEditPopupVisible = true }
                    )
                }

                Text(
                    text = category.strCategoryDescription,
                    textAlign = TextAlign.Justify,
                    style = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .verticalScroll(rememberScrollState())
                )
                if (isAddOrEditPopupVisible) {
                    AddOrEditCommentPopup(
                        navController = navController,
                        viewModel = viewModel,
                        category = category,
                        onDismiss = { isAddOrEditPopupVisible = false }
                    )
                }
            }
        }
    }
}

@Composable
fun CommentView(
    comment: String,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        OutlinedTextField(
            value = comment,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Comment") },
            readOnly = true
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { onEdit() }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = { onDelete() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}