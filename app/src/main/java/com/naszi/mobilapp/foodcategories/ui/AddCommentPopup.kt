package com.naszi.mobilapp.foodcategories.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
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
import com.naszi.mobilapp.foodcategories.R
import com.naszi.mobilapp.foodcategories.model.Category
import com.naszi.mobilapp.foodcategories.model.database.Comment
import com.naszi.mobilapp.foodcategories.viewmodel.MainViewModel

@Composable
fun AddCommentPopup(
    category: Category,
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    var commentText by remember { mutableStateOf("") }


    AlertDialog(
        backgroundColor = colorResource(id = R.color.teal_200),
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Comment",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            OutlinedTextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.white)
                ),
                value = commentText,
                onValueChange = {
                    commentText = it
                },
                label = {
                    Text(text = "Comment")
                }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    val comment = Comment(
                        categoryItemId = category.idCategory.toInt(),
                        comment = commentText
                    )
                    viewModel.addComment(comment)
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.white)
                )
            ) {
                Text(
                    text = "Send Comment",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        }
    )
}