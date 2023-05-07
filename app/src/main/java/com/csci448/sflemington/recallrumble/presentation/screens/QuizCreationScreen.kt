package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.sflemington.recallrumble.data.CategoryRepository
import com.csci448.sflemington.recallrumble.data.MutableQuiz
import com.csci448.sflemington.recallrumble.presentation.components.QuizCreationQuestion

@Composable
fun QuizCreationScreen(quiz : MutableQuiz, onQuizSaved: ()->Unit){

    //val myQuiz = remember{mutableStateOf(quiz)}
    LazyColumn{
        item {
            Text(text = "Title", fontSize = 20.sp)
            TextField( modifier = Modifier.fillMaxWidth(), value = quiz.title.value, onValueChange = {quiz.title.value = it})
            Text(text = "Category", fontSize = 20.sp)
            CategoriesDropdown(quiz = quiz)
        }
        itemsIndexed(quiz.questionList) {i, question->
            QuizCreationQuestion(question, i)
            Spacer(Modifier.height(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesDropdown(quiz: MutableQuiz) {
    val categories = CategoryRepository.categoryList
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(quiz.category.value) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = stringResource(id = selectedCategory.category),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = category.category))},
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            selectedCategory = category
                            expanded = false
                            quiz.category.value = category
                        }
                    )
                }
            }
        }
    }
}