package com.csci448.sflemington.recallrumble.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.Category
import com.csci448.sflemington.recallrumble.data.CategoryRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(categories: List<Category>) {
    val context = LocalContext.current
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()//width(300.dp)
        ) {
            items(categories) {
                Card(
                    modifier = Modifier
                        .padding(11.dp)
                        .height(122.dp)
                        .clickable{
                            Log.d("448.CategoryListScreen", "Category clicked!")
                            Toast.makeText(context,
                            "You will be taken into an online match",
                            Toast.LENGTH_SHORT)
                        }
                ) {
                    Column(Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Text(
                            text = stringResource(id = it.category),
                            fontSize = 23.sp,
                            textAlign = TextAlign.Center
                        )
                        Image(painter = painterResource(id = it.graphic), contentDescription = "")
                    }
                }
            }
        }
}


@Preview
@Composable
fun PreviewCategoryListScreen() {
    val previewList = CategoryRepository.categoryList
    CategoryListScreen(categories = previewList)
}
