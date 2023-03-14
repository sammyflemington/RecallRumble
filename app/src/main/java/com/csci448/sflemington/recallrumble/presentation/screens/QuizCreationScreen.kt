package com.csci448.sflemington.recallrumble.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csci448.sflemington.recallrumble.data.Question
import com.csci448.sflemington.recallrumble.presentation.components.QuizCreationQuestion

@Composable
fun QuizCreationScreen(){
    val questionCount = 3
    LazyColumn{
        items(questionCount) {i ->
            QuizCreationQuestion(Question(0, listOf(0,0,0,0)), i)
            Spacer(Modifier.height(10.dp))
        }
    }

}