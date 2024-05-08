package com.naszi.mobilapp.foodcategories.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.naszi.mobilapp.foodcategories.R
import com.naszi.mobilapp.foodcategories.model.CategoryWithComment
import com.naszi.mobilapp.foodcategories.model.database.Comment
import com.naszi.mobilapp.foodcategories.navigation.Screen
import com.naszi.mobilapp.foodcategories.viewmodel.MainViewModel

@Composable
fun AddOrEditCommentPopup(
    navController: NavController,
    category: CategoryWithComment?,
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    var commentText by remember { mutableStateOf(category?.comment ?: "") }

    AlertDialog(
        backgroundColor = colorResource(id = R.color.teal_200),
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (category?.hasComment == false) "Add Comment" else "Edit Comment",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },

        text = {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.white)
                ),
                value = commentText,
                onValueChange = {
                    commentText = it
                }
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val comment = Comment(
                        id = category?.id ?: 0,
                        categoryItemId = category?.idCategory?.toInt() ?: 0,
                        comment = commentText
                    )
                    if (category?.hasComment == false) {
                        viewModel.addComment(comment)
                    } else {
                        viewModel.updateComment(category?.idCategory?.toInt() ?: 0, comment)
                    }
                    onDismiss()
                    navController.navigate(Screen.CategoryScreen.route)
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Save",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Cancel",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }
    )
}